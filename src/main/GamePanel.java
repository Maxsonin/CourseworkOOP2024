package main;

import inputs.KeyboardInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private GameWorld gameWorld;

    public GamePanel(GameWorld gameWorld) {
        mouseInputs = new MouseInputs(gameWorld);
        this.gameWorld = gameWorld;

        setPanelSize();

        addKeyListener(new KeyboardInputs(gameWorld));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameWorld.Render(g);
    }
}