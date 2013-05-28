package horsequeen;

import horsequeen.gamelogic.HorseQueenGame;
import horsequeen.gamelogic.Movement;
import horsequeen.ia.AlphaBetaSearch;
import horsequeen.gamelogic.PositioningHeuristic;
import horsequeen.gamelogic.QueenStackHeuristic;
import horsequeen.iu.OPanel;
import horsequeen.iu.Tablero;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author josue
 */
public class Main extends JFrame{
    
    private Tablero tablero;
    private OPanel opciones;
    
    public Main() {
        this.setSize(new Dimension(600, 800));
        this.setVisible(true);
        
        tablero= new Tablero(8, 8,600,600);
        tablero.setVisible(true);
        this.add(tablero,BorderLayout.PAGE_END);
        
        opciones= new OPanel();
        opciones.setVisible(true);
        opciones.setBackground(Color.lightGray);
        this.add(opciones,BorderLayout.PAGE_START);
        
        pack();
        
    }
    
    @Override
    public void paint(Graphics g){
        super.paint(g);
    }
    
    public static void main (String []args) throws IOException{
       // Main main = new Main();
        HorseQueenGame game = new HorseQueenGame();
        game.setWhitePlayerHeuristic(new PositioningHeuristic());
        game.setBlackPlayerHeuristic(new QueenStackHeuristic());
        AlphaBetaSearch search = new AlphaBetaSearch(game);
        Movement move;
        while(!game.isTerminal(game.getActualStatus())){
            move= (Movement)search.makeDecision(game.getActualStatus());
            game.move(move);
            game.getActualStatus().showState();
            System.out.println(search.getMetrics());
            System.in.read();
        }
        
        
    }
      
}
