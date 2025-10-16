package rock_paper_scissors.view;

import javax.swing.*;
import java.awt.*;

public class InitialView extends JPanel {
    JButton randomModeButton;
    JButton endlessTrialButton;
    JLabel titleLabel;

    public InitialView() {
        setLayout(new GridBagLayout());
        setBackground(new Color(250, 250, 250));

        titleLabel = new JLabel("Rock Paper Scissors", SwingConstants.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(new Color(66, 133, 244));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));

        randomModeButton = new JButton("🎲 Random Mode");
        endlessTrialButton = new JButton("♾️ Endless Trial");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 0, 20, 0);
        add(titleLabel, gbc);

        gbc.gridy = 1;
        gbc.insets = new Insets(10, 0, 10, 0);
        add(randomModeButton, gbc);

        gbc.gridy = 2;
        add(endlessTrialButton, gbc);
    }
}
