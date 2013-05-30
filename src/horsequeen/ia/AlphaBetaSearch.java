package horsequeen.ia;

import horsequeen.ia.Metrics;


public class AlphaBetaSearch<STATE, ACTION, PLAYER> implements
        AdversarialSearch<STATE, ACTION> {

   
    Game<STATE, ACTION, PLAYER> game;
    private int expandedNodes;
    private double time;
    private int movements;
    private int p;

    public AlphaBetaSearch(Game<STATE, ACTION, PLAYER> game, int p) {
        this.game = game;
        movements=0;
        this.p=p;
    }

    @Override
    public ACTION makeDecision(STATE state) {
        expandedNodes = 0;
        time=System.currentTimeMillis();
        ACTION result = null;
        double resultValue = Double.NEGATIVE_INFINITY;
        PLAYER player = game.getPlayer(state);
        for (ACTION action : game.getActions(state)) {
            double value = minValue(game.getResult(state, action), player,
                    Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, 0);
            if (value >= resultValue) {
                result = action;
                resultValue = value;
            }
        }
        movements++;
        time = System.currentTimeMillis()-time;
        return result;
    }

    public double maxValue(STATE state, PLAYER player, double alpha, double beta, int p) {
        expandedNodes++;
        p++;
        if (game.isTerminal(state) || p > this.p) {
            return game.getUtility(state, player);
        }
        double value = Double.NEGATIVE_INFINITY;
        for (ACTION action : game.getActions(state)) {
            value = Math.max(value, minValue( //
                    game.getResult(state, action), player, alpha, beta, p));
            if (value >= beta) {
                return value;
            }
            alpha = Math.max(alpha, value);
        }
        return value;
    }

    public double minValue(STATE state, PLAYER player, double alpha, double beta, int p) {
        expandedNodes++;
        p++;
        if (game.isTerminal(state) || p > this.p) {
            return game.getUtility(state, player);
        }
        double value = Double.POSITIVE_INFINITY;
        for (ACTION action : game.getActions(state)) {
            value = Math.min(value, maxValue( //
                    game.getResult(state, action), player, alpha, beta, p));
            if (value <= alpha) {
                return value;
            }
            beta = Math.min(beta, value);
        }
        return value;
    }

    @Override
    public Metrics getMetrics() {
        Metrics result = new Metrics();
        result.set("expandedNodes", expandedNodes);
        result.set("time",time/1000);
        result.set("movements", movements);
        return result;
    }
}
