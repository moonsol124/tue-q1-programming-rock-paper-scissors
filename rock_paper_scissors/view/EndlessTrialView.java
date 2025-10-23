package rock_paper_scissors.view;
import java.net.URL;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class EndlessTrialView extends JPanel {
    JPanel topPanel;
    JPanel centerPanel;
    JPanel bottomPanel;
    
    JLabel announcement;
    JLabel aiThinkingLabel;

    JLabel winCountLabel;
    JLabel roundsLeftLabel;
    JLabel scoreLabel;

    JButton rockButton;
    JButton paperButton;
    JButton scissorsButton;
    JButton finishGameButton;
    JButton backButton;
    JPanel summaryPanel;


    
    public EndlessTrialView() {
        setLayout(new BorderLayout());
        setBackground(new Color(167, 199, 231));
        setBorder(new CompoundBorder(
            new LineBorder(Color.DARK_GRAY, 2, true),
            new EmptyBorder(20, 20, 20, 20)
        ));

        topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        statsPanel.setOpaque(false);
        Font statsFont = new Font("Segoe UI", Font.BOLD, 14);
        winCountLabel = new JLabel("Win Count: 0");
        roundsLeftLabel = new JLabel("Rounds Left: 0");
        scoreLabel = new JLabel("Score: 0");

        winCountLabel.setFont(statsFont);
        roundsLeftLabel.setFont(statsFont);
        scoreLabel.setFont(statsFont);

        statsPanel.add(winCountLabel);
        statsPanel.add(roundsLeftLabel);
        statsPanel.add(scoreLabel);

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        buttonsPanel.setOpaque(false);
        finishGameButton = new JButton("🏁 Finish Game");
        backButton = new JButton("⬅ Back");
        buttonsPanel.add(finishGameButton);
        buttonsPanel.add(backButton);

        topPanel.add(statsPanel, BorderLayout.WEST);
        topPanel.add(buttonsPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // === CENTER PANEL ===
        centerPanel = new JPanel(new BorderLayout());
        centerPanel.setOpaque(false);

        announcement = new JLabel("Make your move!", SwingConstants.CENTER);
        announcement.setFont(new Font("Segoe UI", Font.BOLD, 24));
        announcement.setForeground(new Color(33, 33, 33));

        aiThinkingLabel = new JLabel("", SwingConstants.CENTER);
        aiThinkingLabel.setFont(new Font("Segoe UI", Font.ITALIC, 18));
        aiThinkingLabel.setForeground(new Color(90, 90, 90));

        centerPanel.add(announcement, BorderLayout.CENTER);
        centerPanel.add(aiThinkingLabel, BorderLayout.SOUTH);
        add(centerPanel, BorderLayout.CENTER);

        // === BOTTOM PANEL ===
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 15));
        bottomPanel.setOpaque(false);
        
        rockButton = createImageButton("rock-ezgif.com-effects.gif");
        paperButton = createImageButton("paper-ezgif.com-resize.gif");
        scissorsButton = createImageButton("scissor-ezgif.com-resize.gif");

        bottomPanel.add(rockButton);
        bottomPanel.add(paperButton);
        bottomPanel.add(scissorsButton);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JButton createImageButton(String imagePath) {
    URL imgURL = getClass().getResource("/rock_paper_scissors/assets/" + imagePath);
    if (imgURL != null) {
        ImageIcon icon = new ImageIcon(imgURL);
        JButton btn = new JButton(icon);
        btn.setPreferredSize(new Dimension(120, 120)); // control button size
        btn.setFocusPainted(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Optional: center and scale within button
        btn.setHorizontalAlignment(SwingConstants.CENTER);
        btn.setVerticalAlignment(SwingConstants.CENTER);

        // Add a faint border on hover (no scaling!)
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBorder(BorderFactory.createLineBorder(new Color(52, 152, 219), 3, true));
            }

            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBorder(null);
            }
        });

        return btn;
    } else {
        System.err.println("Couldn't find file: " + imagePath);
        return new JButton("Image not found");
    }
}


    
    public JPanel getSummaryPanel(int wins, int losses, int rounds) {
        summaryPanel = new JPanel(new BorderLayout());
        summaryPanel.setBackground(new Color(255, 249, 196));
        summaryPanel.setBorder(new CompoundBorder(
            new LineBorder(Color.DARK_GRAY, 2, true),
            new EmptyBorder(40, 40, 40, 40)
        ));

        JLabel summaryLabel = new JLabel(String.format(
            "<html><center><h2>Endless Mode Summary</h2>"
            + "<p><b>Wins:</b> %d</p>"
            + "<p><b>Losses:</b> %d</p>"
            + "<p><b>Total Rounds:</b> %d</p></center></html>",
            wins, losses, rounds
        ), SwingConstants.CENTER);
        summaryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 20));

        JButton backToMenu = new JButton("⬅ Back to Menu");
        backToMenu.setFont(new Font("Segoe UI", Font.BOLD, 16));

        JPanel bottom = new JPanel();
        bottom.setOpaque(false);
        bottom.add(backToMenu);

        summaryPanel.add(summaryLabel, BorderLayout.CENTER);
        summaryPanel.add(bottom, BorderLayout.SOUTH);

        return summaryPanel;
    }
    
    // GETTERS 
    public JButton getRockButton() { 
        return rockButton; 
    }
    
    public JButton getPaperButton() { 
        return paperButton; 
    }
    
    public JButton getScissorsButton() { 
        return scissorsButton; 
    }
    
    public JButton getFinishGameButton() { 
        return finishGameButton; 
    }
    
    public JButton getBackButton() { 
        return backButton; 
    }

    public JLabel getAnnouncementLabel() { 
        return announcement; 
    }
    
    public JLabel getAIThinkingLabel() { 
        return aiThinkingLabel; 
    }

    public JLabel getWinCountLabel() { 
        return winCountLabel; 
    }
    
    public JLabel getRoundsLeftLabel() { 
        return roundsLeftLabel; 
    }
    
    public JLabel getScoreLabel() { 
        return scoreLabel; 
    }
}
        

