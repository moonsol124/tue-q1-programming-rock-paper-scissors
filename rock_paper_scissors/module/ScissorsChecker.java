package rock_paper_scissors.module;

import rock_paper_scissors.abstracts.RPSChoice;
import rock_paper_scissors.interfaces.ChoiceChecker;

/**
 * The scissors checker implements the ChoiceChecker interface
 * and has the function check() that determines who won, given that
 * the user has played scissors.
 */

public class ScissorsChecker implements ChoiceChecker {
    RPSChoice userChoice;

    public void setUserChoice(RPSChoice userChoice) {
        this.userChoice = userChoice;
    }

    /**
    * This method allows to set userchoice to each checker
    * to easily determine who won the round.
    */
    public Boolean check(RPSChoice computerChoice) {
        Boolean won = false;
        
        if (userChoice.getName().equals("Scissors") && computerChoice.getName().equals("Paper")) {
            won = true;
        }

        return won;
    }
}
