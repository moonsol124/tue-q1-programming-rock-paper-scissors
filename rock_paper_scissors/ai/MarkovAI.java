package rock_paper_scissors.ai;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MarkovAI implements RpsAI{
    private static final String[] MOVES = {"Rock", "Paper", "Scissors"};
    private final Map<String, double[]> transitions = new HashMap<>();
    private final Random rand = new Random();
    
    private String last1 = null;
    private String last2 = null;
    private String last3 = null;
    private static final double SMOOTH = 0.5;
    private static final double DECAY = 0.9;
    private String lastPrediction = "Rock";

    @Override
    public String nextMove() {
        if (last1 == null || last2 == null || last3 == null) {
            return  MOVES[rand.nextInt(3)];
        }

        String key = last1 + "," + last2 + "," + last3;
        double[] probs = transitions.getOrDefault(key, new double[]{1, 1, 1});

        int maxIndex = 0;
        for (int i = 1; i < 3; i++) {
            if (probs[i] > probs[maxIndex]) {
                maxIndex = i;
            }
        }

        String predictedPlayer = MOVES[maxIndex];
        lastPrediction = predictedPlayer;
        return counter(predictedPlayer);
    }
    
    @Override
    public void  observe(String playerMove, String aiMove, boolean playerWon) {
        if (last1 != null && last2 != null && last3 != null) {
            String key = last1 + "," + last2 + "," + last3;
            double[] probs = transitions.getOrDefault(key, new double[]{SMOOTH, SMOOTH, SMOOTH});

            for (int i = 0; i < probs.length; i++) {
                probs[i] *= DECAY;
            }
            switch (playerMove) {
                case "Rock" -> probs[0]++;
                case "Paper" -> probs[1]++;
                case "Scissors" -> probs[2]++;
                default -> {
                }
            }
            transitions.put(key, probs);
        }
        last1 = last2;
        last2 = last3;
        last3 = playerMove;
    }

    private String counter(String move) {
        return switch (move) {
            case "Rock" -> "Paper";
            case "Paper" -> "Scissors";
            default -> "Rock";
        };
    }

    public String getLastPrediction() { 
        return lastPrediction; 
    }   
}
    

