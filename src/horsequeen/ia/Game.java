package horsequeen.ia;

import java.util.List;


public interface Game<STATE, ACTION, PLAYER> {

    /**
     * Obtiene el estado inicial del juego
     * @return 
     */
	STATE getInitialState();

        /**
         * Obtiene los jugadores del juego
         * @return 
         */
	PLAYER[] getPlayers();
        
        /**
         * Obtiene el jugador actual del estado pasado por parametro
         * @param state
         * @return 
         */

	PLAYER getPlayer(STATE state);
        /**
         * Obtiene una lista de posibles acciones a realizar a partir del estado
         * pasado por parametro
         * @param state
         * @return 
         */

	List<ACTION> getActions(STATE state);

        /**
         * Obtiene el estado resultante de aplicar la accion pasada como segundo
         * parametro al estado pasado como primer parametro
         * @param state
         * @param action
         * @return 
         */
	STATE getResult(STATE state, ACTION action);

        /**
         * Analiza si el estado pasado por parametro es terminal
         * @param state
         * @return 
         */
	boolean isTerminal(STATE state);

        /**
         * Devuelve un valor numerico que representa al utilidad del estado
         * pasado como primer parametro para el jugador pasado como segundo
         * @param state
         * @param player
         * @return 
         */
	double getUtility(STATE state, PLAYER player);
}
