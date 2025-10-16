package rock_paper_scissors.abstracts;

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
    public abstract void play();
    
    public String getName() {
        return this.name;
    }

    public abstract RPSChoice getChoice();
}
