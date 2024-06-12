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
        this.gameWorld = gameWorld;
        this.mouseInputs = new MouseInputs(gameWorld);

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(new KeyboardInputs(gameWorld));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

        setPanelSize();
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        gameWorld.render(g);
    }
}
