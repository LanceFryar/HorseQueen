package horsequeen.gamelogic;

/**
 * Esta clase representa el estado actual del juego.
 * @author josue
 */
public class Game {
    
    private GameStatus actualStatus;

    public Game() {
    }
    
    public void move(Movement movement){
        actualStatus.move(movement);
    }
    
    
    
    
}
