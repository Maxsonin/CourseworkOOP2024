package Map;

import Utils.Loader;
import Utils.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

public class Map {
    private static final String MAP_IMG = "Map.png";
    private final Vector2<Integer> imgSize = new Vector2<>(3348, 1912);

    private Vector2<Integer> viewPos = new Vector2<>(0, 0);
    private double scalePercentage = 1;
    private final int amountOfDisplacement = 100;

    private BufferedImage mapImg;
    private BufferedImage subImage;


    public Map() {
        mapImg = Loader.GetSprite(MAP_IMG);
    }

    public int GetAmountOfDisplacement() { return amountOfDisplacement; }

    public void setScalePercentage(double scalePercentage) {
        viewPos = new Vector2<>(0, 0);
        this.scalePercentage = scalePercentage;
    }
    public double getScalePercentage() { return scalePercentage; }

    public void Update() {

    }

    public void Draw(Graphics g) {
        subImage = mapImg.getSubimage(viewPos.getX(), viewPos.getY(), (int)(imgSize.getX() * scalePercentage), (int)(imgSize.getY() * scalePercentage));
        g.drawImage(subImage, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
        subImage.flush();
    }

    public Vector2<Integer> GetView() { return viewPos; }

    public void moveViewDown(int amount) {
        int newY = viewPos.getY() + amount;
        int maxAllowedY = (int)(imgSize.getY() * (1 - scalePercentage));
        viewPos.setY(Math.min(newY, maxAllowedY));
    }

    public void moveViewUp(int amount) {
        viewPos.moveY(-amount);
        if (viewPos.getY() < 0) {
            viewPos.setY(0);
        }
    }

    public void moveViewLeft(int amount) {
        viewPos.moveX(-amount);
        if (viewPos.getX() < 0) {
            viewPos.setX(0);
        }
    }

    public void moveViewRight(int amount) {
        int newX = viewPos.getX() + amount;
        int maxAllowedX = (int)(imgSize.getX() * (1 - scalePercentage));
        viewPos.setX(Math.min(newX, maxAllowedX));
    }
}