package horsequeen;

import horsequeen.gamelogic.HorseQueenGame;
import horsequeen.gamelogic.Movement;
import horsequeen.ia.AlphaBetaSearch;
import horsequeen.gamelogic.PositioningHeuristic;
import horsequeen.gamelogic.QueenStackHeuristic;
import horsequeen.iu.OPanel;
import horsequeen.iu.Tablero;
import horsequeen.util.Position;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import java.util.Scanner;
import javax.swing.JFrame;

/**
 *
 * @author josue
 */
public class Main extends JFrame {

    private Tablero tablero;
    private OPanel opciones;

    public Main() {
        this.setSize(new Dimension(600, 800));
        this.setVisible(true);

        tablero = new Tablero(8, 8, 600, 600);
        tablero.setVisible(true);
        this.add(tablero, BorderLayout.PAGE_END);

        opciones = new OPanel();
        opciones.setVisible(true);
        opciones.setBackground(Color.lightGray);
        this.add(opciones, BorderLayout.PAGE_START);

        pack();

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
    }

    public static void main(String[] args) throws IOException {
        // Main main = new Main();
        Scanner sc = new Scanner(System.in);
        HorseQueenGame game = new HorseQueenGame();
        AlphaBetaSearch search = new AlphaBetaSearch(game);
        Movement move;
        System.out.print("¿Blancas(0) o negras(1)?");
        int choose = sc.nextInt();
        if (choose == 0) {
            game.setBlackPlayerHeuristic(new QueenStackHeuristic());
        } else {
            game.setWhitePlayerHeuristic(new PositioningHeuristic());
        }
        game.getActualStatus().showState();



        while (!game.isTerminal(game.getActualStatus())) {

            /*move= (Movement)search.makeDecision(game.getActualStatus());
             game.move(move);
             game.getActualStatus().showState();
             System.out.println(search.getMetrics());
             System.in.read();*/
            if (game.getPlayer(game.getActualStatus()) == choose) {
                System.out.print("Inserte columna fuente: ");
                int initCol = sc.nextInt();
                System.out.print("Inserte fila fuente: ");
                int initRow = sc.nextInt();
                System.out.print("Inserte columna destino: ");
                int destCol = sc.nextInt();
                System.out.print("Inserte fila destino: ");
                int destRow = sc.nextInt();
                move = new Movement(new Position(initCol, initRow),
                        new Position(destCol, destRow));
            } else {
                move = (Movement) search.makeDecision(game.getActualStatus());
            }
            game.move(move);
            game.getActualStatus().showState();
        }


    }
}
