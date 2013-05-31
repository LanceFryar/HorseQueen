package horsequeen.iu;

import horsequeen.gamelogic.HorseQueenGame;
import horsequeen.gamelogic.HorseQueenStatus;
import horsequeen.gamelogic.Movement;
import horsequeen.gamelogic.Queen;
import horsequeen.util.Position;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tabletop extends JPanel implements clickListener {

    private TabletopCell[][] paneles;
    private int rows, columns;
    private int width, height;
    private Color color1, color2;
    private HorseQueenGame game;
    private Position lastMoved;
    private boolean movement;
    private LogPanel logPanel;

    public Tabletop(int rows, int columns, int width, int height, 
            HorseQueenGame game, LogPanel logPanel) {
        this.movement = false;
        this.rows = rows;
        this.columns = columns;
        this.width = width;
        this.height = height;
        this.game = game;
        this.logPanel=logPanel;
        this.setSize(new Dimension(width, height));
        this.setMaximumSize(new Dimension(width, height));
        this.setMinimumSize(new Dimension(width, height));
        this.setPreferredSize(new Dimension(width, height));
        paneles = new TabletopCell[rows][columns];
        color1 = Color.blue;
        color2 = Color.white;
        this.setLayout(new GridLayout(rows, columns));
        int dx = width / rows, dy = height / columns;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if ((j + i) % 2 == 0) {
                    paneles[i][j] = new TabletopCell(i, j, dx, dy, color1);
                } else {
                    paneles[i][j] = new TabletopCell(i, j, dx, dy, color2);
                }
                this.add(paneles[i][j]);
                paneles[i][j].AddOnClickListener(this);
                paneles[i][j].setBoton(new JButton("X"));
            }
        }
        update();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.black);
    }

    @Override
    public void onClick(Position pos) {
        if (!isMoving()) {
            if ((game.getPlayer(game.getActualStatus()) == HorseQueenStatus.WHITE
                    && paneles[pos.getRow()][pos.getCol()].getBoton().getText().charAt(0) == 'W')
                    || (game.getPlayer(game.getActualStatus()) == HorseQueenStatus.BLACK
                    && paneles[pos.getRow()][pos.getCol()].getBoton().getText().charAt(0) == 'B')) {
                movement = true;
                List<Movement> moves = this.game.getActualStatus().getPosibleMovementsFor(pos);
                for (Movement move : moves) {
                    lastMoved = pos;
                    paneles[move.getDestination().getRow()][move.getDestination().getCol()].setPosibleMovement(Color.red);
                    paneles[move.getDestination().getRow()][move.getDestination().getCol()].getBoton().setVisible(true);
                    this.repaint();
                }
            }
        } else {
            movement = false;
            List<Movement> moves = this.game.getActualStatus().getPosibleMovementsFor(lastMoved);
            game.getActualStatus().move(new Movement(lastMoved, pos));
            paneles[pos.getRow()][pos.getCol()].unsetPosibleMovement();
            for (Movement move : moves) {
                if (!pos.equals(move.getDestination())) {
                    paneles[move.getDestination().getRow()][move.getDestination().getCol()].unsetPosibleMovement();
                    paneles[move.getDestination().getRow()][move.getDestination().getCol()].getBoton().setVisible(false);

                }
            }
            update();
            this.repaint();
        }
    }

    private boolean isMoving() {
        return movement;
    }

    public void update() {
        if (game.getActualStatus().isTerminal()){
            logPanel.writeLog(game.getActualStatus().getWiner()
                            + "ganan");
        }
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Position position = new Position(i, j);
                if (game.getActualStatus().getBoard().getPieceAt(position) == null) {
                    paneles[i][j].getBoton().setVisible(false);
                    paneles[i][j].getBoton().setText("X");
                } else {
                    paneles[i][j].getBoton().setVisible(true);
                    if (game.getActualStatus().getBoard().getPieceAt(position).
                            getColor() == HorseQueenStatus.WHITE) {
                        if (game.getActualStatus().getBoard().getPieceAt(position) instanceof Queen) {
                            paneles[i][j].getBoton().setText("WQ"
                                    + game.getActualStatus().getWhiteQueen().getStack());

                        } else {
                            paneles[i][j].getBoton().setText("WB");
                        }
                    } else {
                        if (game.getActualStatus().getBoard().getPieceAt(position) instanceof Queen) {
                            paneles[i][j].getBoton().setText("BQ"
                                    + game.getActualStatus().getBlackQueen().getStack());

                        } else {
                            paneles[i][j].getBoton().setText("BB");
                        }

                    }

                }
            }

        }
    }
}
