package horsequeen.ia;


public interface AdversarialSearch<STATE, ACTION> {

    public static final int P = 6;

    /**
     * Devuelve la accion que estima como mejor dado el estado pasado por 
     * parametro
     */
    ACTION makeDecision(STATE state);

    /**
     * Devuelve las metricas de la busqueda
     *
     * @return all the metrics of the search.
     */
    Metrics getMetrics();
}
