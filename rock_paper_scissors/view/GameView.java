package rock_paper_scissors.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import rock_paper_scissors.ai.EnsembleAI;
import rock_paper_scissors.ai.RandomAI;
import rock_paper_scissors.controller.*;
import rock_paper_scissors.interfaces.*;
import rock_paper_scissors.model.*;
import rock_paper_scissors.module.*;


/**
 * goal is to use
 * 1. inheritance
 * 2. polymorphism
 * 3. abstract classes / interfaces / subclasses
 * 4. objected oriented programming
 * 5. division of methods
 * 6. lisvoke substitution
 * 
 * idea:
 * 1. game play option with abstract class thus inheritance: rock, paper, scissors
 * 2. game rule checker with interface: rock checker, paper checker, scissor checker
 * 3. decides who wins, by using the rule checkers, 
 * thus applying polymorphism and lisvoke substitution
 * 
 * how game will be done
 * 1. inital screen: choose game mode, endless trial or random
 * 2. random mode:
 * 2.1 three buttons: rock, paper, scissor
 * 2.2 win/lose count
 * 2.3 celebration or boo, depending on if you lose or win
 * 
 * 3. endless trial mode:
 * 3.1 four buttons: rock, paper, scissor, stop
 * 3.2 win/lose count
 * 3.3 application of the algorithm
        what steps i took to learn stuff for github
        algorithm.       
 */

/*
 * This game class represents the graphic user interface and its components.
 * It is composed of components such as panels, buttons and frames.
 * The event listeners listen to those components and do a variety of things such as
 * changing texts, panels, updating round numbers and number of wins.
 * The actual logics behind the GUI are implemented in other classes.
 * The focus of this class is mainly the GUI.
 */
public class GameView {
    User user;
    Computer computer;
    ChoiceChecker[] checkers;
    int BORDER_SIZE;
    int ROUND_DELAY_MS = 500;
    int COUNT_DOWN_TIME_MS = 300; // keep your chosen countdown step
    int winCount = 0;
    int computerWinCount = 0;
    int roundsPlayed = 0;
    final int TOTAL_ROUNDS = 3;
    RoundController roundController;
    boolean isEndlessMode = false;

    JFrame frame;
    JPanel gameBackgroundPanel;
    JPanel initialPanel;
    JPanel randomModePanel;
    JPanel endlessTrialPanel;
    
    EndlessTrialView etView; 
    // center area uses its own CardLayout so announcement and result can coexist
    CardLayout centerCardLayout;
    JPanel centerPanel;
    String resultText;

    // shared UI
    JLabel titleLabel;
    JLabel announcement;
    JLabel winCountLabel;
    JLabel roundsLeftLabel;
    JLabel modeLabel;

    CardLayout cardLayout;

    // Random Mode labels
    JLabel randomWinLabel;
    JLabel randomRoundsLabel;
    JLabel randomAnnouncement;

    // Endless Mode labels
    JLabel endlessWinLabel;
    JLabel endlessAnnouncement;

    
    // Random panel buttons
    JButton rockButtonRandom;
    JButton paperButtonRandom;
    JButton scissorsButtonRandom;
    JButton randomModeButton;
    JButton backRandomButton;
    
    // Endless panel buttons
    JButton rockButtonEndless;
    JButton paperButtonEndless;
    JButton scissorsButtonEndless;
    JButton endlessTrialButton;
    
    JButton backEndlessButton;
    JButton finishGameButton;

    // result UI (prebuilt in constructor)
    JPanel resultPanel;
    JLabel finalLabel;
    JLabel againLabel;
    JButton yesButton;
    JButton noButton;

    javax.swing.Timer countdownTimer;
    javax.swing.Timer nextRoundTimer;
    int countdownValue;

    public GameView() {
        BORDER_SIZE = 20;
        checkers = new ChoiceChecker[]{new RockChecker(), new PaperChecker(), new ScissorsChecker()};
        user = new User("User");
        computer = null; // will be assigned when mode is chosen

        // Frame setup
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame("Rock Paper Scissors");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(screenSize.width / 2, screenSize.height / 2);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(new Color(245, 245, 250));

        // Layout setup
        cardLayout = new CardLayout();
        gameBackgroundPanel = new JPanel(cardLayout);
        gameBackgroundPanel.setBackground(new Color(245, 245, 245));
        gameBackgroundPanel.setBorder(new EmptyBorder(BORDER_SIZE, BORDER_SIZE, BORDER_SIZE, BORDER_SIZE));
        
        // Create a dedicated title panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setOpaque(true);
        titlePanel.setBackground(new Color(66, 133, 244));
        titlePanel.setBorder(new EmptyBorder(20, 10, 20, 10));

        // Create main title label
        titleLabel = new JLabel("Rock Paper Scissors", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));

        // Create mode label
        modeLabel = new JLabel("Mode: None", SwingConstants.CENTER);
        modeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        modeLabel.setForeground(Color.WHITE);

        // Add them into the panel
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.add(modeLabel, BorderLayout.SOUTH);


        announcement = new JLabel("", SwingConstants.CENTER);
        announcement.setFont(new Font("Segoe UI", Font.BOLD, 26));
        announcement.setForeground(new Color(33, 33, 33));

        winCountLabel = new JLabel("Win Count: 0");
        roundsLeftLabel = new JLabel("Rounds Left: " + TOTAL_ROUNDS);
        Font statsFont = new Font("Segoe UI", Font.PLAIN, 16);
        winCountLabel.setFont(statsFont);
        roundsLeftLabel.setFont(statsFont);
        modeLabel = new JLabel("Mode: None");
        modeLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        modeLabel.setForeground(Color.DARK_GRAY);


        // Buttons
        Color btnColor = new Color(52, 152, 219);
        Color accentColor = new Color(231, 76, 60);
        Color neutralColor = new Color(46, 204, 113);

        rockButtonRandom = new JButton("🪨 Rock");
        paperButtonRandom = new JButton("📄 Paper");
        scissorsButtonRandom = new JButton("✂️ Scissors");
        rockButtonEndless = new JButton("🪨 Rock");
        paperButtonEndless = new JButton("📄 Paper");
        scissorsButtonEndless = new JButton("✂️ Scissors");
        randomModeButton = new JButton("🎲 Random Mode");
        endlessTrialButton = new JButton("♾️ Endless Trial");
        backRandomButton = new JButton("⬅ Back");
        //backEndlessButton = new JButton("⬅ Back");
        finishGameButton = new JButton("🏁 Finish Game");
        yesButton = new JButton("✅ Yes");
        noButton = new JButton("❌ No");

        // Style all buttons
        styleButton(rockButtonRandom, btnColor, Color.WHITE);
        styleButton(paperButtonRandom, btnColor, Color.WHITE);
        styleButton(scissorsButtonRandom, btnColor, Color.WHITE);
        styleButton(rockButtonEndless, btnColor, Color.WHITE);
        styleButton(paperButtonEndless, btnColor, Color.WHITE);
        styleButton(scissorsButtonEndless, btnColor, Color.WHITE);
        styleButton(randomModeButton, neutralColor, Color.WHITE);
        styleButton(endlessTrialButton, neutralColor, Color.WHITE);
        styleButton(backRandomButton, accentColor, Color.WHITE);
        //styleButton(backEndlessButton, accentColor, Color.WHITE);
        styleButton(finishGameButton, accentColor, Color.WHITE);
        styleButton(yesButton, neutralColor, Color.WHITE);
        styleButton(noButton, accentColor, Color.WHITE);

        // initial panel
        InitialView initView = new InitialView();
        initialPanel = initView;

        randomModeButton = initView.randomModeButton;
        endlessTrialButton = initView.endlessTrialButton;
        // titleLabel = initView.titleLabel;

        // Random Mode Panel 
        RandomModeView rmView = new RandomModeView();
        randomModePanel = rmView;

        centerPanel = rmView.getCenterPanel();
        centerCardLayout = (CardLayout) centerPanel.getLayout();
        
        JPanel topRandomPanel = new JPanel(new BorderLayout());

        randomWinLabel = rmView.getWinCountLabel();
        randomRoundsLabel = rmView.getRoundsLeftLabel();
        randomAnnouncement = rmView.getAnnouncementLabel();

        rockButtonRandom = rmView.getRockButton();
        paperButtonRandom = rmView.getPaperButton();
        scissorsButtonRandom = rmView.getScissorsButton();
        backRandomButton = rmView.getBackButton();
        
        // Link them into GameView
        announcement = randomAnnouncement;
        winCountLabel = randomWinLabel;
        roundsLeftLabel = randomRoundsLabel;

        // Add the random mode panel directly (no re-adding centerPanel again)
        gameBackgroundPanel.add(randomModePanel, "random");

        // result card
        resultPanel = new JPanel(new GridBagLayout());
        resultPanel.setOpaque(false);
        GridBagConstraints gbcResult = new GridBagConstraints();
        gbcResult.gridx = 0;
        gbcResult.gridy = 0;

        finalLabel = new JLabel("", SwingConstants.CENTER);
        finalLabel.setFont(new Font("Segoe UI", Font.BOLD, 34));
        finalLabel.setForeground(new Color(33, 33, 33));
        resultPanel.add(finalLabel, gbcResult);

        gbcResult.gridy = 1;
        gbcResult.insets = new Insets(10, 0, 10, 0);
        againLabel = new JLabel("Wanna play again?");
        againLabel.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        resultPanel.add(againLabel, gbcResult);

        gbcResult.gridy = 2;

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonsPanel.setOpaque(false);
        buttonsPanel.add(yesButton);
        buttonsPanel.add(noButton);
        resultPanel.add(buttonsPanel, gbcResult);

        centerPanel.add(resultPanel, "result");
        centerCardLayout.show(centerPanel, "announce");

        randomModePanel.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomRandomPanel = rmView.bottomPanel;

        bottomRandomPanel.setOpaque(false);
        bottomRandomPanel.add(rockButtonRandom);
        bottomRandomPanel.add(paperButtonRandom);
        bottomRandomPanel.add(scissorsButtonRandom);
        randomModePanel.add(bottomRandomPanel, BorderLayout.SOUTH);

        disableChoices();

        // endless trial panel
        etView = new EndlessTrialView();
        endlessTrialPanel = etView;

        endlessWinLabel = etView.getWinCountLabel();
        roundsLeftLabel = etView.getRoundsLeftLabel();

        rockButtonEndless = etView.getRockButton();
        paperButtonEndless = etView.getPaperButton();
        scissorsButtonEndless = etView.getScissorsButton();
        finishGameButton = etView.getFinishGameButton();
        //backEndlessButton = etView.getBackButton();
        
        endlessTrialPanel = etView;

        // Optional: connect labels if you want to update text dynamically
        announcement = etView.getAnnouncementLabel();

        // Add the panel to the card layout
        gameBackgroundPanel.add(endlessTrialPanel, "endless");
        
        rockButtonEndless = etView.getRockButton();
        paperButtonEndless = etView.getPaperButton();
        scissorsButtonEndless = etView.getScissorsButton();
        finishGameButton = etView.getFinishGameButton();
        //backEndlessButton = etView.getBackButton();

        gameBackgroundPanel.add(initialPanel, "initial");
        gameBackgroundPanel.add(randomModePanel, "random");
        gameBackgroundPanel.add(endlessTrialPanel, "endless");
        
        JPanel root = new JPanel(new BorderLayout());
        root.add(gameBackgroundPanel, BorderLayout.CENTER);
        frame.setContentPane(root);

    }

    private void playRound(String playerChoice) {
    // In random mode we block input during the countdown.
    if (!isEndlessMode && countdownTimer != null && countdownTimer.isRunning()) {
        return;
    }

    disableChoices();
    stopCountdownTimer();
    stopNextRoundTimer();

    user.setChoice(playerChoice);
    roundController = new RoundController(checkers, computer, user);

    // Use the correct labels for the active mode
    final JLabel activeAnnouncement = isEndlessMode ? etView.getAnnouncementLabel() : randomAnnouncement;
    final JLabel activeWinLabel     = isEndlessMode ? endlessWinLabel                 : randomWinLabel;

    // --- "AI thinking..." animation
    final javax.swing.Timer thinkingDots = new javax.swing.Timer(300, null);
    final int[] dotCount = {0};
    thinkingDots.addActionListener(ev -> {
        dotCount[0] = (dotCount[0] + 1) % 4;
        activeAnnouncement.setText("🤔 AI is thinking" + ".".repeat(dotCount[0]));
    });
    thinkingDots.start();

    // Let AI "think" then resolve the round
    roundController.determineWinnerWithDelay(activeAnnouncement, () -> {
        boolean playerWon = roundController.determineWinner();
        thinkingDots.stop();

        if (playerWon) {
            resultText = "You win!";
            winCount++;
        } else if (roundController.determineDraw()) {
            resultText = "Draw!";
        } else {
            resultText = "You Lose!";
            computerWinCount++;          // increment only once
        }

        // update counters
        roundsPlayed++;
        activeWinLabel.setText("Win Count: " + winCount);

        if (isEndlessMode) {
            etView.getRoundsLeftLabel().setText("Rounds: " + roundsPlayed);
            etView.getScoreLabel().setText("Score: " + (winCount - computerWinCount));
        } else {
            randomRoundsLabel.setText("Rounds Left: " + (TOTAL_ROUNDS - roundsPlayed));
        }

        // show round result
        String html = "<html><div style='text-align:center; font-size:18px;'><b>" + resultText +
                "</b><br>You: " + roundController.getUserchoiceName() 
                +
                "<br>Computer: " + roundController.getComputerChoiceName() + "</div></html>";
        activeAnnouncement.setText(html);

        // --- ONE timer that decides what happens after the short delay
        javax.swing.Timer postRoundDelay = new javax.swing.Timer(ROUND_DELAY_MS, e -> {
            if (isEndlessMode) {
                enableChoices();                       // immediate next round
            } else if (roundsPlayed >= TOTAL_ROUNDS) {
                endGame();                             // show result card
            } else {
                startCountdown();                      // countdown to next random-mode round
            }
        });
        postRoundDelay.setRepeats(false);
        postRoundDelay.start();
    });
    }

    
    public void play() {
        // listners
        randomModeButton.addActionListener(e -> {
            // Use a RandomAI for Random Mode
            modeLabel.setText("Mode: 🎲 Random (Easy)");
            isEndlessMode = false;
            enterRandomMode();

        });

        endlessTrialButton.addActionListener(e -> {
            stopAllTimers();
            // Use MarkovAI (predictive) for Endless Mode
            computer = new Computer("AI", new EnsembleAI());
            modeLabel.setText("Mode: ♾️ Endless (Smart)");
            isEndlessMode = true;
            enterEndlessMode();
        });

        backRandomButton.addActionListener(e -> {
            int choice = JOptionPane.showConfirmDialog(
                frame,
                "Are you sure you want to quit this game?",
                "Exit Game",
                JOptionPane.YES_NO_OPTION
            );

            if (choice == JOptionPane.YES_OPTION) {
                // Stop all timers and disable buttons
                stopAllTimers();
                disableChoices();

                // Reset game state variables
                winCount = 0;
                computerWinCount = 0;
                roundsPlayed = 0;

                // Reset AI and round logic
                computer = null;
                roundController = null;

                // Reset the randomModePanel's UI to its initial state for the next game
                randomWinLabel.setText("Win Count: 0");
                randomRoundsLabel.setText("Rounds Left: " + TOTAL_ROUNDS);
                randomAnnouncement.setText("Select your choice!"); // Clear the announcement text
                centerCardLayout.show(centerPanel, "announce"); // IMPORTANT: Show the announcement panel, not the results panel

                // Go back to the main screen
                cardLayout.show(gameBackgroundPanel, "initial");
            }
        });


        // backEndlessButton.addActionListener(e -> {
        //     stopAllTimers();
        //     cardLayout.show(gameBackgroundPanel, "initial");
        // });
        finishGameButton.addActionListener(e -> {
        stopAllTimers();

        // Compute total rounds and win rates
        int total = winCount + computerWinCount;
        double userRate = total > 0 ? (100.0 * winCount / total) : 0;
        double aiRate = total > 0 ? (100.0 * computerWinCount / total) : 0;

        // Build a simple summary message
        String summaryHtml = String.format(
            "<html><center><h2>Game Over</h2>"
            + "<p><b>Wins:</b> %d</p>"
            + "<p><b>Losses:</b> %d</p>"
            + "<p><b>Total Rounds:</b> %d</p>"
            + "<p><b>Your Win Rate:</b> %.1f%%</p>"
            + "<p><b>AI Win Rate:</b> %.1f%%</p></center></html>",
            winCount, computerWinCount, roundsPlayed, userRate, aiRate
        );

        JLabel summaryLabel = new JLabel(summaryHtml, SwingConstants.CENTER);
        summaryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 22));

        JPanel summaryPanel = new JPanel(new BorderLayout());
        summaryPanel.setBackground(new Color(255, 249, 196));
        summaryPanel.setBorder(new CompoundBorder(
            new LineBorder(Color.DARK_GRAY, 2, true),
            new EmptyBorder(40, 40, 40, 40)
        ));
        summaryPanel.add(summaryLabel, BorderLayout.CENTER);

        JButton backToMenu = new JButton("⬅ Back to Menu");
        styleButton(backToMenu, new Color(231, 76, 60), Color.WHITE);
        backToMenu.addActionListener(ev -> {
            // Reset EndlessTrialView labels to initial state
            etView.getWinCountLabel().setText("Win Count: 0");
            etView.getRoundsLeftLabel().setText("Rounds: 0");
            etView.getAnnouncementLabel().setText("Make your move!");
            if (etView.getScoreLabel() != null) {
                etView.getScoreLabel().setText("Score: 0");
            }

            // Also reset counters
            winCount = 0;
            computerWinCount = 0;
            roundsPlayed = 0;

            cardLayout.show(gameBackgroundPanel, "initial");
        });

        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.add(backToMenu);
        summaryPanel.add(bottom, BorderLayout.SOUTH);

        // Reset internal counters
        winCount = 0;
        computerWinCount = 0;
        roundsPlayed = 0;

        // Add and show this temporary summary panel
        gameBackgroundPanel.add(summaryPanel, "summary");
        cardLayout.show(gameBackgroundPanel, "summary");
    });


        // listen to the player's input
        // Random mode buttons
        rockButtonRandom.addActionListener(e -> {
            isEndlessMode = false;
            playRound("Rock");
        });
        paperButtonRandom.addActionListener(e -> {
            isEndlessMode = false;
            playRound("Paper");
        });
        scissorsButtonRandom.addActionListener(e -> {
            isEndlessMode = false;
            playRound("Scissors");
        });

        // Endless mode buttons
        rockButtonEndless.addActionListener(e -> {
            isEndlessMode = true;
            playRound("Rock");
        });
        paperButtonEndless.addActionListener(e -> {
            isEndlessMode = true;
            playRound("Paper");
        });
        scissorsButtonEndless.addActionListener(e -> {
            isEndlessMode = true;
            playRound("Scissors");
        });

        // result panel
        yesButton.addActionListener(e -> {
            resultPanel.setVisible(false);
            centerCardLayout.show(centerPanel, "announce");
            randomAnnouncement.setText("Select your choice!"); // Clear the announcement text
            enterRandomMode();
        });

        noButton.addActionListener(e -> {
            resultPanel.setVisible(false);
            centerCardLayout.show(centerPanel, "announce");
            randomAnnouncement.setText("Select your choice!"); // Clear the announcement text
            cardLayout.show(gameBackgroundPanel, "initial");
        });

        frame.setVisible(true);
    }

    private void enterRandomMode() {
        stopAllTimers();
        
        // Always start a brand new AI and clean state
        computer = new Computer("AI", new RandomAI());
        roundController = null;
        
        winCount = 0;
        computerWinCount = 0;
        roundsPlayed = 0;
        
        winCountLabel.setText("Win Count: 0");
        roundsLeftLabel.setText("Rounds Left: " + TOTAL_ROUNDS);
        announcement.setText("");
        centerCardLayout.show(centerPanel, "announce");
        cardLayout.show(gameBackgroundPanel, "random");
        
        enableChoices();
        startCountdown();
    }

    private void enterEndlessMode() {
        stopAllTimers();
        winCount = 0;
        computerWinCount = 0;
        roundsPlayed = 0;

        // Reset screen text
        announcement.setText("Make your move!");
        winCountLabel.setText("Win Count: 0");

        // Switch to the endless panel
        cardLayout.show(gameBackgroundPanel, "endless");

        // Allow immediate playing (no countdown)
        enableChoices();
    }


    private void startCountdown() {
        stopAllTimers();
        if (roundsPlayed >= TOTAL_ROUNDS) {
            return;
        }
        countdownValue = 3;
        announcement.setText("Choose your option in... " + countdownValue);
        disableChoices();

        countdownTimer = new javax.swing.Timer(COUNT_DOWN_TIME_MS, e -> {
            countdownValue--;
            if (countdownValue > 0) {
                announcement.setText("Choose your option in... " + countdownValue);
            } else {
                stopCountdownTimer();
                announcement.setText("GO!");
                enableChoices();
            }
        });
        countdownTimer.setInitialDelay(COUNT_DOWN_TIME_MS);
        countdownTimer.start();
    }

    private void endGame() {
        stopAllTimers();
        disableChoices();

        String finalMessage = "";
        if (winCount > computerWinCount) {
            finalMessage = "You win!";
        } else if (winCount == computerWinCount) {
            finalMessage = "Draw!";
        } else {
            finalMessage = "You lose!";
        }
        finalLabel.setText(finalMessage);

        // switch to result card
        centerCardLayout.show(centerPanel, "result");
    }

    private void stopAllTimers() {
        stopCountdownTimer();
        stopNextRoundTimer();
    }

    private void stopCountdownTimer() {
        if (countdownTimer != null && countdownTimer.isRunning()) {
            countdownTimer.stop();
        }
        countdownTimer = null;
    }

    private void stopNextRoundTimer() {
        if (nextRoundTimer != null && nextRoundTimer.isRunning()) {
            nextRoundTimer.stop();
        }
        nextRoundTimer = null;
    }

    private void disableChoices() {
        rockButtonRandom.setEnabled(false);
        paperButtonRandom.setEnabled(false);
        scissorsButtonRandom.setEnabled(false);
        rockButtonEndless.setEnabled(false);
        paperButtonEndless.setEnabled(false);
        scissorsButtonEndless.setEnabled(false);
    }

    private void enableChoices() {
        rockButtonRandom.setEnabled(true);
        paperButtonRandom.setEnabled(true);
        scissorsButtonRandom.setEnabled(true);
        rockButtonEndless.setEnabled(true);
        paperButtonEndless.setEnabled(true);
        scissorsButtonEndless.setEnabled(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameView game = new GameView();
            game.play();
        });
    }

    private void styleButton(JButton btn, Color bg, Color fg) {
        btn.setFocusPainted(false);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btn.setBorder(new LineBorder(bg.darker(), 2, true));
        btn.setPreferredSize(new Dimension(150, 45));

        // hover effect
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn.setBackground(bg.brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn.setBackground(bg);
            }
        });
    }

    private JPanel createCardPanel(Color bgColor) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(bgColor);
        panel.setBorder(new CompoundBorder(
                new LineBorder(Color.DARK_GRAY, 2, true),
                new EmptyBorder(20, 20, 20, 20)
        ));
        return panel;
    }
    
    private void topPanelSetupForRandomMode(RandomModeView rmView) {
        JPanel topRandomPanel = rmView.topPanel;
        topRandomPanel.setOpaque(false);
    }

    private void fadeText(JLabel label, String newText, Color color) {
        Timer fadeTimer = new Timer(30, null);
        final float[] alpha = {0f}; // start transparent

        label.setForeground(new Color(color.getRed(), color.getGreen(), color.getBlue(), 0));
        label.setText(newText);

        fadeTimer.addActionListener(e -> {
            alpha[0] += 0.05f; // control speed here
            if (alpha[0] >= 1f) {
                alpha[0] = 1f;
                ((Timer) e.getSource()).stop();
            }
            label.setForeground(new Color(color.getRed(), color.getGreen(), color.getBlue(),
                Math.min(1f, alpha[0])));
        });

        fadeTimer.start();
    }

    
}