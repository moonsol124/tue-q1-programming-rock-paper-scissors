package rock_paper_scissors.model;

import rock_paper_scissors.abstracts.Player;
import rock_paper_scissors.abstracts.RPSChoice;

/**
 * User player that extends 'Player' superclass.
 * It represents the users,
 */
public class User extends Player {
    String input;
    RPSChoice choice;

    public User(String name) {
        super(name);
    }

    public void setChoice(String input) {
        this.input = input;
    }

    /**
     * The user input will be converted in one of te following subclasses of 'Choice'.
     * Either rock, paper, or scissors.
     */
    @Override
    public void play() {
        RPSChoice option = new Rock();

        if (this.input.equals("Paper")) {
            option = new Paper();
        }
        if (this.input.equals("Scissors")) {
            option = new Scissors();
        }

        System.out.println(option.getName());
        this.choice = option;
    }

    public RPSChoice getChoice() {
        return this.choice;
    }
}
