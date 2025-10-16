package rock_paper_scissors.controller;

import rock_paper_scissors.abstracts.*;
import rock_paper_scissors.model.*;
import rock_paper_scissors.interfaces.*;

public class RoundController {
    RPSChoice userChoice;
    RPSChoice computerChoice;
    Player computer;
    Player user;
    ChoiceChecker[] checkers;

    public RoundController(ChoiceChecker[] checkers, Player computer, Player user) {
        this.checkers = checkers;
        this.user = user;
        this.computer = computer;
    }

    public boolean determineDraw() {
        return userChoice.getName().equals(computerChoice.getName());
    }

    public boolean determineWinner() {
        computer.play();
        computerChoice = computer.getChoice();
        user.play(); 
        userChoice = user.getChoice();
        
        Round round = new Round(checkers);
        
        boolean result = false;
        result = round.go(userChoice, computerChoice);

        return result;
    }

    public String getUserchoiceName() {
        return user.getChoice().getName();
    }

    public String getComputerChoiceName() {
        return computer.getChoice().getName();
    }
}
