package horsequeen;

import horsequeen.gamelogic.HorseQueenGame;
import horsequeen.gamelogic.HorseQueenStatus;
import horsequeen.gamelogic.Movement;
import horsequeen.gamelogic.PositioningHeuristic;
import horsequeen.gamelogic.PossibleMovementsHeuristic;
import horsequeen.ia.AdversarialSearch;
import horsequeen.ia.AlphaBetaSearch;
import horsequeen.ia.Heuristic;
import horsequeen.iu.OPanel;
import horsequeen.iu.Tablero;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author josue
 */
public class Main extends JFrame {

    private Tablero tablero;
    private OPanel opciones;
    HorseQueenGame horseQueenGame;
    AdversarialSearch search;

    public Main() {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(new Dimension(801, 601));
        this.setVisible(true);
        this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        horseQueenGame = new HorseQueenGame();
        horseQueenGame.setWhitePlayerHeuristic(new PositioningHeuristic());
        horseQueenGame.setBlackPlayerHeuristic(new PositioningHeuristic());
        search = new AlphaBetaSearch(horseQueenGame, 2);
        
       
        
        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reset();
            }
        });
        
        JButton makeDecisionButton = new JButton("Make decision");
        makeDecisionButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                opciones.getDesicionLabel().setVisible(true);
                Movement move = 
                        (Movement)
                        search.makeDecision(horseQueenGame.getActualStatus());
                horseQueenGame.move(move);
                opciones.getDesicionLabel().setVisible(false);
                tablero.update();
                
            }
        });
        
        JComboBox<Heuristic> whiteHeuristicComboBox = new JComboBox<>();
        whiteHeuristicComboBox.addItem(new PositioningHeuristic());
        whiteHeuristicComboBox.addItem(new PossibleMovementsHeuristic());
        whiteHeuristicComboBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                horseQueenGame.setWhitePlayerHeuristic((Heuristic) e.getItem());
            }
        });
        
        JComboBox<Heuristic> blackHeuristicComboBox = new JComboBox<>();
        blackHeuristicComboBox.addItem(new PositioningHeuristic());
        blackHeuristicComboBox.addItem(new PossibleMovementsHeuristic());
        blackHeuristicComboBox.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                horseQueenGame.setBlackPlayerHeuristic((Heuristic) e.getItem());
            }
        });
        
        
        

        opciones = new OPanel(600, 80);
        opciones.setResetButton(resetButton);
        opciones.setMakeDecisionButton(makeDecisionButton);
        opciones.setWhiteHeuristicComboBox(whiteHeuristicComboBox);
        opciones.setBlackHeuristicComboBox(blackHeuristicComboBox);
        opciones.setDesicionLabel();
        opciones.setVisible(true);
        opciones.setBackground(Color.lightGray);
        this.add(opciones);
        
        tablero = new Tablero(HorseQueenStatus.ROWS, HorseQueenStatus.COLS,
                600, 600, horseQueenGame);
        tablero.setVisible(true);
        this.add(tablero);
        pack();

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    public static void main(String[] args) {
        Main main = new Main();

    }

    private void reset() {
        remove(tablero);
        horseQueenGame = new HorseQueenGame();
        horseQueenGame.setWhitePlayerHeuristic(new PositioningHeuristic());
        horseQueenGame.setBlackPlayerHeuristic(new PositioningHeuristic());
        search = new AlphaBetaSearch(horseQueenGame, 2);
        tablero = new Tablero(HorseQueenStatus.ROWS, HorseQueenStatus.COLS,
                600, 600, horseQueenGame);
        add(tablero);
        repaint();
        pack();
    }
}
