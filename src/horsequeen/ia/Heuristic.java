/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package horsequeen.ia;

import horsequeen.gamelogic.HorseQueenStatus;

/**
 *
 * @author josue
 */
public interface Heuristic {
    /**
     *
     * @return
     */
     
     public double h(HorseQueenStatus status, int player);
}
