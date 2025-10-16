package rock_paper_scissors.module;

import rock_paper_scissors.interfaces.ChoiceChecker;
import rock_paper_scissors.model.RPSChoice;

/**
 * The rock checker implements the ChoiceChecker interface
 * and has the function check() that determines who won, given that
 * the user has played rock.
 */
public class RockChecker implements ChoiceChecker {
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
        
        if (userChoice.getName().equals("Rock") && computerChoice.getName().equals("Scissors")) {
            won = true;
        }

        return won;
    }
}
