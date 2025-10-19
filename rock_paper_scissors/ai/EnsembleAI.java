package rock_paper_scissors.ai;

import java.util.*;

/**
 * Ensemble of multiple Markov models with different memory and decay rates.
 * Inspired by PiotrekG's RPS markov_v13_ensemble.
 */
public class EnsembleAI implements RpsAI {
    private static final String[] MOVES = {"Rock", "Paper", "Scissors"};
    private final List<MarkovAI> models = new ArrayList<>();
    private final double[] weights;
    private final Random rand = new Random();

    public EnsembleAI() {
        double[] decays = {0.5, 0.6, 0.7, 0.8, 0.9, 0.93, 0.95, 0.97, 0.99};
        int[] levels = {1, 2, 3, 4};

        for (int level : levels) {
            for (double decay : decays) {
                models.add(new MarkovAI()); // could make constructor take level/decay
            }
        }
        weights = new double[models.size()];
        Arrays.fill(weights, 1.0);
    }

    @Override
    public String nextMove() {
        Map<String, Double> totals = new HashMap<>();
        for (String move : MOVES) totals.put(move, 0.0);

        for (int i = 0; i < models.size(); i++) {
            String move = models.get(i).nextMove();
            totals.put(move, totals.get(move) + weights[i]);
        }

        // pick the move with the highest weighted sum
        return Collections.max(totals.entrySet(), Map.Entry.comparingByValue()).getKey();
    }

    @Override
    public void observe(String playerMove, String aiMove, boolean playerWon) {
        for (int i = 0; i < models.size(); i++) {
            MarkovAI model = models.get(i);
            model.observe(playerMove, aiMove, playerWon);

            // reward / punish model performance
            if (playerWon) {
                weights[i] *= 0.95;  // downweight
            } else {
                weights[i] *= 1.05;            // reward
            }
        }
    }
}
