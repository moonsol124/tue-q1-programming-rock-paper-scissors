package rock_paper_scissors.model;

import rock_paper_scissors.abstracts.Player;
import rock_paper_scissors.abstracts.RPSChoice;
import rock_paper_scissors.ai.RandomAI;
import rock_paper_scissors.ai.RpsAI;
/**
 * Computer class represents the computer. It plays randomly against the user.
 */
public class Computer extends Player {
    private RPSChoice choice;
    private final RpsAI ai;

    public Computer(String name, RpsAI ai) {
        super(name);
        // Default to RandomAI if no AI provided
        this.ai = (ai != null) ? ai : new RandomAI();
    }

    /**
     * The computer plays randomly.
     * Either rock, paper, or scissors.
     */
    @Override
    public void play() {
        String computerMove = ai.nextMove();
        // Convert the string into the corresponding RPSChoice subclass
        choice = switch (computerMove) {
            case "Paper" -> new Paper();
            case "Scissors" -> new Scissors();
            default -> new Rock();
        };
    }

    public void observe(String playerMove, boolean playerWon) {
        if (ai != null && choice != null) {
            ai.observe(playerMove, choice.getName(), playerWon);
        }
    }

    @Override
    public RPSChoice getChoice() {
        return choice;
    }
}
