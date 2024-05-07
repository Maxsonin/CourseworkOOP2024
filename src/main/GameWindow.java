package main;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    public GameWindow(GamePanel gamePanel, GameWorld gameWorld) {
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.add(gamePanel);
        this.setJMenuBar(new MenuBar(gameWorld));
        this.setResizable(false);
        this.pack();
        this.setVisible(true);

        centerWindow(this);
    }

    private void centerWindow(JFrame frame) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) ((screenSize.getWidth() - frame.getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - frame.getHeight()) / 2);
        frame.setLocation(centerX, centerY);
    }
}