package rock_paper_scissors.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class RandomModeView extends JPanel {
    JPanel topPanel;
    JPanel centerPanel;
    JPanel bottomPanel;

    JLabel winCountLabel;
    JLabel roundsLeftLabel;
    JLabel announcement;

    JButton rockButton;
    JButton paperButton;
    JButton scissorsButton;
    JButton backButton;

    public RandomModeView() {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 235, 238));
        setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY, 2, true),
                new EmptyBorder(20, 20, 20, 20)));

        // Top panel with stats
        topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        statsPanel.setOpaque(false);
        winCountLabel = new JLabel("Win Count: 0");
        roundsLeftLabel = new JLabel("Rounds Left: 3");
        statsPanel.add(winCountLabel);
        statsPanel.add(roundsLeftLabel);

        backButton = new JButton("⬅ Back");

        topPanel.add(statsPanel, BorderLayout.WEST);
        topPanel.add(backButton, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);

        // Center panel with announcement
        centerPanel = new JPanel(new CardLayout());
        centerPanel.setOpaque(false);
        announcement = new JLabel("", SwingConstants.CENTER);
        announcement.setFont(new Font("Segoe UI", Font.BOLD, 26));
        announcement.setForeground(new Color(33, 33, 33));

        // AI Thinking Label
        JLabel aiThinkingLabel = new JLabel("Make your move!", SwingConstants.CENTER);
        aiThinkingLabel.setFont(new Font("Segoe UI", Font.ITALIC, 18));
        aiThinkingLabel.setForeground(new Color(90, 90, 90));
        
        JPanel announceCard = new JPanel(new BorderLayout());
        announceCard.setOpaque(false);
        announceCard.add(announcement, BorderLayout.CENTER);
        centerPanel.add(announceCard, "announce");
        centerPanel.add(aiThinkingLabel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);

        // Bottom panel with buttons
        bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 25, 15));
        bottomPanel.setOpaque(false);
        rockButton = new JButton("🪨 Rock");
        paperButton = new JButton("📄 Paper");
        scissorsButton = new JButton("✂️ Scissors");

        bottomPanel.add(rockButton);
        bottomPanel.add(paperButton);
        bottomPanel.add(scissorsButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }
    // Getters so controller can access buttons and labels 
    public JButton getRockButton() { 
        return rockButton; 
    }

    public JButton getPaperButton() { 
        return paperButton; 
    }

    public JButton getScissorsButton() { 
        return scissorsButton; 
    }

    public JLabel getAnnouncementLabel() { 
        return announcement; 
    }
}
