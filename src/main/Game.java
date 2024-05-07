package main;

public class Game implements Runnable {
    // Window Settings
    private GameWindow gameWindow;
    private GamePanel gamePanel;

    // FPS and UPS Settings
    private final int FPS_SET = 120;
    private final int UPS_SET = 200;
    private Thread gameThread;

    // Window Settings
    public final static int GAME_WIDTH = 1675;
    public final static int GAME_HEIGHT = 956;

    // Game Word Settings
    GameWorld gameWorld;

    public Game() {
        gameWorld = new GameWorld();

        gamePanel = new GamePanel(gameWorld);
        gameWindow = new GameWindow(gamePanel, gameWorld);
        gamePanel.requestFocus();

        StartGameLoop();
    }

    private void StartGameLoop() {
        gameThread = new Thread(this); gameThread.start();
    }

    // This function implements a game loop that updates the game world and repaints
    // the game panel to achieve a target frames-per-second (FPS) and updates-per-second (UPS) rate
    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SET;
        double timePerUpdate = 1000000000.0 / UPS_SET;

        long previousTime = System.nanoTime();

        int frames = 0; int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaU = 0; double deltaF = 0;
        while(true) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1) {
                gameWorld.Update();
                updates++;
                deltaU--;
            }

            if (deltaF >= 1) {
                gamePanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                //System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;  updates = 0;
            }
        }
    }
}
