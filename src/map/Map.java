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
    private double scalePercentage = 1;
    private final int amountOfDisplacement = 100;

    private BufferedImage mapImg;
    private BufferedImage subImage;


    public Map() {
        mapImg = Loader.GetSprite(SD.Map);
        imgSize = new Dimension(mapImg.getWidth(), mapImg.getHeight());
    }

    public void update() {

    }

    public void draw(Graphics g) {
        subImage = mapImg.getSubimage(viewPos.getX(), viewPos.getY(), (int)(imgSize.getWidth() * scalePercentage), (int)(imgSize.getHeight() * scalePercentage));
        g.drawImage(subImage, 0, 0, GAME_WIDTH, GAME_HEIGHT, null);
        subImage.flush();
    }

    public Vector2<Integer> getView() { return viewPos; }

    public void moveViewDown(GameWorld gameWorld) {
        int oldYPos = viewPos.getY();
        int newY = oldYPos + amountOfDisplacement;
        int maxAllowedY = (int)(imgSize.getHeight() * (1 - scalePercentage));
        viewPos.setY(Math.min(newY, maxAllowedY));

        double amountToMove = oldYPos - Math.min(newY, maxAllowedY);
        double res = 0.0;
        if (scalePercentage == 0.75)  res = (Math.abs(amountToMove) - Math.abs(amountToMove) * (scalePercentage - 0.07)) * 2;
        if (scalePercentage == 0.5)  res = Math.abs(amountToMove) - Math.abs(amountToMove) * (scalePercentage - 0.5);
        gameWorld.moveDown(res);
    }

    public void moveViewUp(GameWorld gameWorld) {
        int oldYPos = viewPos.getY();
        int newY = oldYPos - amountOfDisplacement;
        int minAllowedY = 0;
        viewPos.setY(Math.max(newY, minAllowedY));

        double amountToMove = oldYPos - Math.max(newY, minAllowedY);
        double res = 0.0;
        if (scalePercentage == 0.75)  res = (Math.abs(amountToMove) - Math.abs(amountToMove) * (scalePercentage - 0.07)) * 2;
        if (scalePercentage == 0.5)  res = Math.abs(amountToMove) - Math.abs(amountToMove) * (scalePercentage - 0.5);
        gameWorld.moveUp(res);
    }

    public void moveViewLeft(GameWorld gameWorld) {
        int oldXPos = viewPos.getX();
        int newX = oldXPos - amountOfDisplacement;
        int minAllowedX = 0;
        viewPos.setX(Math.max(newX, minAllowedX));

        double amountToMove = oldXPos - Math.max(newX, minAllowedX);
        double res = 0.0;
        if (scalePercentage == 0.75)  res = (Math.abs(amountToMove) - Math.abs(amountToMove) * (scalePercentage - 0.07)) * 2;
        if (scalePercentage == 0.5)  res = (Math.abs(amountToMove) - Math.abs(amountToMove) * scalePercentage) * 2;
        gameWorld.moveLeft(res);
    }

    public void moveViewRight(GameWorld gameWorld) {
        int oldXPos = viewPos.getX();
        int newX = oldXPos + amountOfDisplacement;
        int maxAllowedX = (int)(imgSize.getWidth() * (1 - scalePercentage));
        viewPos.setX(Math.min(newX, maxAllowedX));

        double amountToMove = oldXPos - Math.min(newX, maxAllowedX);
        double res = 0.0;
        if (scalePercentage == 0.75)  res = (Math.abs(amountToMove) - Math.abs(amountToMove) * (scalePercentage - 0.07)) * 2;
        if (scalePercentage == 0.5)  res = (Math.abs(amountToMove) - Math.abs(amountToMove) * scalePercentage) * 2;
        gameWorld.moveRight(res);
    }

    public void setScalePercentage(double scalePercentage, GameWorld gameWorld) {
        viewPos = new Vector2<>(0, 0);
        this.scalePercentage = scalePercentage;

        for (var base : gameWorld.getBases()) {
            double scaleFactorX = base.getInitialPosition().getX() * (1 - scalePercentage) * 2;
            double scaleFactorY = base.getInitialPosition().getY() * (1 - scalePercentage) * 2;
            if (scalePercentage == 1) {
                scaleFactorX = 0; scaleFactorY = 0;
            }
            Vector2<Double> newBasePos = new Vector2<>(base.getInitialPosition().getX() + scaleFactorX, base.getInitialPosition().getY() + scaleFactorY);
            base.setNewPosition(newBasePos);
        }

        // TODO: FIX ENTITIES MOVEMENT
/*        for (var entity : gameWorld.getEntities()) {
            double scaleFactorX = entity.getPosition().getX() * (1 - scalePercentage);
            double scaleFactorY = entity.getPosition().getY() * (1 - scalePercentage);
            if (scalePercentage == 1) {
                scaleFactorX = 0; scaleFactorY = 0;
            }

            Vector2<Double> newBasePos = new Vector2<>(entity.getPosition().getX() + scaleFactorX, entity.getPosition().getY() + scaleFactorY);
            entity.setNewPosition(newBasePos);
        }*/
    }
}