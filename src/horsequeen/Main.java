package horsequeen;

import horsequeen.gamelogic.HorseQueenGame;
import horsequeen.gamelogic.HorseQueenStatus;
import horsequeen.gamelogic.Movement;
import horsequeen.ia.AlphaBetaSearch;
import horsequeen.ia.PositioningHeuristic;
import horsequeen.ia.QueenStackHeuristic;
import java.io.IOException;

/**
 *
 * @author josue
 */
public class Main /*extends JFrame*/{
    
    /*private Tablero tablero;
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
    }*/
    
    public static void main (String []args) throws IOException{
        HorseQueenGame game = new HorseQueenGame();
        game.setBlackPlayerHeuristic(new PositioningHeuristic());
        game.setWhitePlayerHeuristic(new QueenStackHeuristic());
        AlphaBetaSearch<HorseQueenStatus, Movement,Integer> search = new AlphaBetaSearch(game);
        game.getActualStatus().showState();
        
        while(!game.isTerminal(game.getActualStatus())){

            Movement move = search.makeDecision(game.getActualStatus().clone());
            System.out.println();
            System.out.println("(" + move.getSource().getRow() 
                   + ", "+ move.getSource().getCol() + ") a " 
                    + "(" + move.getDestination().getRow() + ", "
                    + move.getDestination().getCol() + ")");
            System.out.println();
            game.move(move);
            game.getActualStatus().showState();
            System.in.read();
        }
        
    }
      
}
