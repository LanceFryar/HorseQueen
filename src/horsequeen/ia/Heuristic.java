package horsequeen.ia;

import horsequeen.gamelogic.HorseQueenStatus;

/**
 * Clase que representa una heuristica
 * @author josue
 */
public interface Heuristic {
    /**
     * Funcion que devuelve un valor heuristico para un etado dado
     * @return
     */
     
     public double h(HorseQueenStatus status, int player);
     @Override
     public String toString();
}