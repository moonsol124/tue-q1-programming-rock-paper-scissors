package rock_paper_scissors.ai;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MarkovAI implements RpsAI{
    private static final String[] MOVES = {"Rock", "Paper", "Scissors"};
    private final Map<String, double[]> transitions = new HashMap<>();
    private final Random rand = new Random();
    
    private final int level;       // how many past moves to remember
    private final double decay;    // how fast to forget old info
    private final double smooth;   // smoothing for unseen sequences
    
    private String[] lastMoves;
    private String lastPrediction = "Rock";

    public MarkovAI(int level, double decay) {
        this.level = Math.max(1, level);
        this.decay = decay;
        this.smooth = 0.5;
        this.lastMoves = new String[level];
    }

    @Override
    public String nextMove() {
        if (lastMoves[0] == null) {
            return MOVES[rand.nextInt(3)];
        }

        String key = String.join(",", lastMoves);
        double[] probs = transitions.getOrDefault(key, new double[]{smooth, smooth, smooth});

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
    public void observe(String playerMove, String aiMove, boolean playerWon) {
        if (lastMoves[0] != null) {
            String key = String.join(",", lastMoves); // represents the pattern of recent moves
            double[] probs = transitions.getOrDefault(key, new double[]{smooth, smooth, smooth});

            // decay older info
            for (int i = 0; i < probs.length; i++) {
                probs[i] *= decay;
            }

            // reinforce the observed player move
            switch (playerMove) {
                case "Rock" -> probs[0]++;
                case "Paper" -> probs[1]++;
                case "Scissors" -> probs[2]++;
                default -> {
                }
            }

            transitions.put(key, probs);
        }

        // shift memory (forget oldest, add new)
        for (int i = 0; i < level - 1; i++) {
            lastMoves[i] = lastMoves[i + 1];
        }
        lastMoves[level - 1] = playerMove;
    }

    private String counter(String move) {
        return switch (move) {
            case "Rock" -> "Paper";
            case "Paper" -> "Scissors";
            default -> "Rock";
        };
    }

    public int getLevel() { 
        return level; 
    }
    
    public double getDecay() { 
        return decay; 
    }
}
    

