package horsequeen.gamelogic;

import horsequeen.ia.Game;
import horsequeen.ia.Heuristic;
import java.util.List;

/**
 * Esta clase representa el estado actual del juego.
 * @author josue
 */
public class HorseQueenGame implements Game<HorseQueenStatus, Movement, Integer>{
    
    private HorseQueenStatus actualStatus;
    private Heuristic whitePlayerHeuristic;
    private Heuristic blackPlayerHeuristic;

    public HorseQueenGame() {
        actualStatus =  new HorseQueenStatus();
    }

    /**
     * Obtiene la heuristica que usara el jugaro blanco
     * @return  
     */
    public Heuristic getWhitePlayerHeuristic() {
        return whitePlayerHeuristic;
    }
    
    /**
     * Establece la heuristica que usarara el jugador blanco
     * @param whitePlayerHeuristic 
     */

    public void setWhitePlayerHeuristic(Heuristic whitePlayerHeuristic) {
        this.whitePlayerHeuristic = whitePlayerHeuristic;
    }

    /**
     * Obtiene la heuristica que usara el jugaro negro
     * @return  
     */
    public Heuristic getBlackPlayerHeuristic() {
        return blackPlayerHeuristic;
    }

    /**
     * Establece la heuristica que usarara el jugador negro
     * @param whitePlayerHeuristic 
     */
    public void setBlackPlayerHeuristic(Heuristic blackPlayerHeuristic) {
        this.blackPlayerHeuristic = blackPlayerHeuristic;
    }
    
    /**
     * Obtiene el estado actual del juego
     * @return 
     */
    public HorseQueenStatus getActualStatus(){
        return actualStatus;
    }
    


    
    @Override
    public HorseQueenStatus getInitialState() {
        return new HorseQueenStatus();
    }

    @Override
    public Integer[] getPlayers() {
          Integer[] players = new Integer[2];
          players[0] = new Integer(0);
          players[1] = new Integer(1);
          return players;
    }

    @Override
    public Integer getPlayer(HorseQueenStatus state) {
        return Integer.valueOf(state.getActualPlayer());
    }

    @Override
    public List<Movement> getActions(HorseQueenStatus state) {
        return state.getPosibleMovements();
    }

    @Override
    public HorseQueenStatus getResult(HorseQueenStatus state, Movement action) {
        HorseQueenStatus result;
        result = (HorseQueenStatus)state.clone();
        result.move(action);
        return result;
    }

    @Override
    public boolean isTerminal(HorseQueenStatus state) {
        return state.isTerminal();
    }

    @Override
    public double getUtility(HorseQueenStatus state, Integer player) {
        if (player==HorseQueenStatus.WHITE){
            return whitePlayerHeuristic.h(state, player);
        }
        else{
            return blackPlayerHeuristic.h(state, player);
        }
    }

    /**
     * Realiza el moviemiento pasado como parametro en el estado actual
     * @param movement 
     */
    public void move(Movement movement){
        actualStatus.move(movement);
    }
    
    
    
    
}