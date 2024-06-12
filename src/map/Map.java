package map;

import main.GameWorld;
import utils.Loader;
import utils.SD;
import utils.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

public class Map {
    private Dimension imgSize;

    private Vector2<Integer> viewPos = new Vector2<>(0, 0);
    private double scalePercentage = 0.5; // To see 25% of full map // Requirement №33
    private final int amountOfDisplacement = 100;

    private BufferedImage mapImg;
    private BufferedImage subImage;


    public Map() {
        mapImg = Loader.GetSprite(SD.Map);
        imgSize = new Dimension(mapImg.getWidth(), mapImg.getHeight());
    }

    public void update() {  }

    public void draw(Graphics g) {
        subImage = mapImg.getSubimage(viewPos.getX(), viewPos.getY(), (int)(imgSize.getWidth() * scalePercentage), (int)(imgSize.getHeight() * scalePercentage));
        g.drawImage(subImage, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
        subImage.flush();
    }

    // Requirement №34
    public void moveViewDown(GameWorld gameWorld) {
        int oldYPos = viewPos.getY();
        int newY = oldYPos + amountOfDisplacement;
        int maxAllowedY = (int)(imgSize.getHeight()* (1 - scalePercentage));
        viewPos.setY(Math.min(newY, maxAllowedY));

        double amountToMove = oldYPos - Math.min(newY, maxAllowedY);
        gameWorld.moveDown(-amountToMove);
    }
    public void moveViewUp(GameWorld gameWorld) {
        int oldYPos = viewPos.getY();
        int newY = oldYPos - amountOfDisplacement;
        int minAllowedY = 0;
        viewPos.setY(Math.max(newY, minAllowedY));

        double amountToMove = oldYPos - Math.max(newY, minAllowedY);
        gameWorld.moveUp(amountToMove);
    }
    public void moveViewLeft(GameWorld gameWorld) {
        int oldXPos = viewPos.getX();
        int newX = oldXPos - amountOfDisplacement;
        int minAllowedX = 0;
        viewPos.setX(Math.max(newX, minAllowedX));

        double amountToMove = oldXPos - Math.max(newX, minAllowedX);
        gameWorld.moveLeft(amountToMove);
    }
    public void moveViewRight(GameWorld gameWorld) {
        int oldXPos = viewPos.getX();
        int newX = oldXPos + amountOfDisplacement;
        int maxAllowedX = (int)(imgSize.getWidth() * (1 - scalePercentage));
        viewPos.setX(Math.min(newX, maxAllowedX));

        double amountToMove = oldXPos - Math.min(newX, maxAllowedX);
        gameWorld.moveRight(-amountToMove);
    }
}