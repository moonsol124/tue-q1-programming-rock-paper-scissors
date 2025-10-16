package rock_paper_scissors.abstracts;

import rock_paper_scissors.model.RPSChoice;

/**
 * Super class for user and computer.
 */
public abstract class Player {
    String name;

    public Player(String name) {
        this.name = name;
    }

    /**
     * let each players to play either rock, paper or scissors.
     */
    public abstract RPSChoice play();
    
    public String getName() {
        return this.name;
    }
}
