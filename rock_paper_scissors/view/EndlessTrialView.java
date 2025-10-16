package rock_paper_scissors.view;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class EndlessTrialView extends JPanel {
    JPanel topPanel;
    JButton finishGameButton;
    JButton backButton;

    public EndlessTrialView() {
        setLayout(new BorderLayout());
        setBackground(new Color(255, 249, 196));
        setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY, 2, true),
                new EmptyBorder(20, 20, 20, 20)));

        topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        JPanel statsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 5));
        statsPanel.setOpaque(false);
        statsPanel.add(new JLabel("Win Count: 0"));
        statsPanel.add(new JLabel("Rounds Left: 0"));
        statsPanel.add(new JLabel("Score: 0"));

        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 5));
        buttonsPanel.setOpaque(false);
        finishGameButton = new JButton("🏁 Finish Game");
        backButton = new JButton("⬅ Back");
        buttonsPanel.add(finishGameButton);
        buttonsPanel.add(backButton);

        topPanel.add(statsPanel, BorderLayout.WEST);
        topPanel.add(buttonsPanel, BorderLayout.EAST);

        add(topPanel, BorderLayout.NORTH);
    }
}
