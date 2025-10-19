package rock_paper_scissors.controller;

import javax.swing.*;
import rock_paper_scissors.abstracts.*;
import rock_paper_scissors.interfaces.*;
import rock_paper_scissors.model.*;

public class RoundController {
    private RPSChoice userChoice;
    private RPSChoice computerChoice;
    private final Computer computer;
    private final User user;
    private final ChoiceChecker[] checkers;

    public RoundController(ChoiceChecker[] checkers, Computer computer, User user) {
        this.checkers = checkers;
        this.user = user;
        this.computer = computer;
    }

    public boolean determineDraw() {
        return userChoice != null && computerChoice != null &&
               userChoice.getName().equals(computerChoice.getName());
    }

    public boolean determineWinner() {
        computer.play();
        computerChoice = computer.getChoice();
        user.play(); 
        userChoice = user.getChoice();
        
        Round round = new Round(checkers);
        boolean userWon = round.go(userChoice, computerChoice);

        // Let AI learn from this outcome
        computer.observe(userChoice.getName(), userWon);

        return userWon;
    }

    public void determineWinnerWithDelay(JLabel aiThinkingLabel, Runnable onFinish) {
        aiThinkingLabel.setText("🤔 AI is thinking");

        // Animate thinking dots;
        javax.swing.Timer thinkingDots = new javax.swing.Timer(300, null);
        final int[] dotCount = {0};
        thinkingDots.addActionListener(e -> {
            dotCount[0] = (dotCount[0] + 1) % 4;
            aiThinkingLabel.setText("🤔 AI is thinking"  + ".".repeat(dotCount[0]));
        });
        thinkingDots.start();

        // After 2s, stop thinking and compute result
        new javax.swing.Timer(2000, e -> {
            thinkingDots.stop();

            computer.play();
            computerChoice = computer.getChoice();
            
            user.play();
            userChoice = user.getChoice();

            // Reveal AI choice before calling the game result
            aiThinkingLabel.setText("🧩 AI chose: " + computerChoice.getName());
            
            // Let AI learn from this round
            Round round = new Round(checkers);
            boolean userWon = round.go(userChoice, computerChoice);
            computer.observe(userChoice.getName(), userWon);
            
            // Notify view to update stats
            onFinish.run();
        }) {{
                setRepeats(false);
                start();
            }};
    }
    
    public String getUserchoiceName() {
        return user.getChoice().getName();
    }

    public String getComputerChoiceName() {
        return computer.getChoice().getName();
    }
}