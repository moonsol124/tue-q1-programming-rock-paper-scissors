package rock_paper_scissors.view;

import java.awt.*;
import java.net.URL;
import javax.swing.*;

public class InitialView extends JPanel {
    JButton randomModeButton;
    JButton endlessTrialButton;
    private Image backgroundImage;

    public InitialView() {
        // Load the background image as a resource
        URL imgURL = getClass().getResource("/rock_paper_scissors/assets/Descargar Rock Paper Scissors Line Vector iconos gratis.jpg");
        if (imgURL != null) {
            backgroundImage = new ImageIcon(imgURL).getImage();
        } else {
            System.err.println("Couldn't find background image!");
        }

        // === Use BorderLayout ===
        setLayout(new BorderLayout());
        setOpaque(true);
        setBackground(Color.BLACK); // fallback color

        // === Create bottom button panel ===
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 40, 30));
        buttonPanel.setOpaque(false); // make it transparent

        randomModeButton = new JButton("🎲 Random Mode");
        endlessTrialButton = new JButton("♾️ Endless Trial");

        // Style the buttons
        styleButton(randomModeButton);
        styleButton(endlessTrialButton);

        // Optional: tweak button colors
        randomModeButton.setBackground(new Color(167, 199, 231)); 
        randomModeButton.setForeground(new Color(30, 61, 255));
        endlessTrialButton.setBackground(new Color(30, 61, 255));
        endlessTrialButton.setForeground(Color.WHITE);

        // Add both buttons to one horizontal row
        buttonPanel.add(randomModeButton);
        buttonPanel.add(endlessTrialButton);

        // Place the row at the bottom
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            Graphics2D g2d = (Graphics2D) g.create();

            int panelWidth = getWidth();
            int panelHeight = getHeight();

            int imgWidth = backgroundImage.getWidth(this);
            int imgHeight = backgroundImage.getHeight(this);

            // Maintain proportions (cover style)
            double scale = Math.max(
                (double) panelWidth / imgWidth,
                (double) panelHeight / imgHeight
            );

            int newWidth = (int) (imgWidth * scale);
            int newHeight = (int) (imgHeight * scale);

            int x = (panelWidth - newWidth) / 2;
            int y = (panelHeight - newHeight) / 2;

            // Draw background image
            g2d.drawImage(backgroundImage, x, y, newWidth, newHeight, this);

            // Light overlay (to make buttons more visible)
            float alpha = 0.15f; 
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            g2d.setColor(Color.WHITE);
            g2d.fillRect(0, 0, panelWidth, panelHeight);

            g2d.dispose();
        }
    }

    private void styleButton(JButton btn) {
        btn.setFont(new Font("Segoe UI", Font.BOLD, 22));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2, true));
        btn.setPreferredSize(new Dimension(250, 50));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }
}
