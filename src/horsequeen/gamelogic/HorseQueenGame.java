package horsequeen.gamelogic;

import horsequeen.ia.Game;
import java.util.List;

/**
 * Esta clase representa el estado actual del juego.
 * @author josue
 */
public class HorseQueenGame implements Game<HorseQueenStatus, Movement, Integer>{
    
    private HorseQueenStatus actualStatus;

    public HorseQueenGame() {
        actualStatus =  new HorseQueenStatus();
    }
    
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
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HorseQueenStatus getResult(HorseQueenStatus state, Movement action) {
        HorseQueenStatus result;
        result = (HorseQueenStatus)state.clone();
        state.move(action);
        return state;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isTerminal(HorseQueenStatus state) {
        return state.isTerminal();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double getUtility(HorseQueenStatus state, Integer player) {
        return state.getUtility();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    
    
    
    
}
