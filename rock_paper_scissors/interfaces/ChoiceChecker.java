package rock_paper_scissors.interfaces;

import rock_paper_scissors.model.RPSChoice;

/**
 * This is the interface that will be implemented by
 * each of the followings: rock, scissors and paper checkers.
 */
public interface ChoiceChecker {
    Boolean check(RPSChoice computerChoice);
    
    /**
     * This method allows to set userchoice to each checker
     * to easily determine who won the round.
     */
    void setUserChoice(RPSChoice userChoice);
}
