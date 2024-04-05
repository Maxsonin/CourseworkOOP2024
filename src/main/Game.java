package main;

import Bases.NaziBase;
import map.Map;
import utils.Point;

import java.awt.*;

public class Game implements Runnable {
    private GameWindow gameWindow;
    private GamePanel gamePanel;

    private Thread gameThread;

    private final int FPS_SET = 120;
    private final int UPS_SET = 200;

    public final static int GAME_WIDTH = 1675;
    public final static int GAME_HEIGHT = 956;

    private Map map;

    NaziBase naziBaseVinnitsa;
    NaziBase naziBaseKrasnohrad;
    NaziBase naziBaseYalta;

    public Map GetMap() {
        return map;
    }

    public Game() {
        InitClasses();

        gamePanel = new GamePanel(this);
        gameWindow = new GameWindow(gamePanel, map);
        gamePanel.requestFocus();

        StartGameLoop();
    }

    public void InitClasses() {
        //map = new Map();
        naziBaseVinnitsa = new NaziBase(map, new Point<>(200.0, 100.0));
        naziBaseVinnitsa.InitializeBaseSettings(new Color(Color.BLUE.getRGB()), "Werwolf Nazi Base");

        naziBaseKrasnohrad = new NaziBase(map, new Point<>(200.0, 350.0));
        naziBaseKrasnohrad.InitializeBaseSettings(new Color(Color.RED.getRGB()), "Secret Nazi Base in Krasnohrad");

        naziBaseYalta = new NaziBase(map, new Point<>(200.0, 550.0));
        naziBaseYalta.InitializeBaseSettings(new Color(Color.YELLOW.getRGB()), "Yalta \"DZIDZIO\" Nazi Base");
    }

    private void StartGameLoop() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void Update() {
        //map.Update();
        naziBaseVinnitsa.Update();
        naziBaseKrasnohrad.Update();
        naziBaseYalta.Update();
    }

    public void Render(Graphics g) {
        //map.Draw(g);
        naziBaseVinnitsa.Draw(g);
        naziBaseKrasnohrad.Draw(g);
        naziBaseYalta.Draw(g);
    }

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
                Update();
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
