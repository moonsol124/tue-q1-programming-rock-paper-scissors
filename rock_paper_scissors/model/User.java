package rock_paper_scissors.model;

import rock_paper_scissors.abstracts.Player;
import rock_paper_scissors.abstracts.RPSChoice;

/**
 * User player that extends 'Player' superclass.
 * It represents the users,
 */
public class User extends Player {
    private RPSChoice choice;

    public User(String name) {
        super(name);
    }

    public void setChoice(String choiceName) {
        choice = switch (choiceName) {
            case "Paper" -> new Paper();
            case "Scissors" -> new Scissors();
            default -> new Rock(); // default to Rock if unknown
        };
    }

    /**
     * The user input will be converted in one of te following subclasses of 'Choice'.
     * Either rock, paper, or scissors.
     */
    @Override
    public void play() {
    }   

    @Override
    public RPSChoice getChoice() {
        return choice;
    }
}
