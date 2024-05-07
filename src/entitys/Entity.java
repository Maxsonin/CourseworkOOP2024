// Base Entity class which sets main properties for every entity in the Game

package entitys;

import java.awt.*;
import java.awt.image.BufferedImage;

import utils.Loader;
import utils.Vector2;

public abstract class Entity {
    // Positioning
    protected double velocity;
    protected Vector2<Double> position;

    // Image
    protected String filePath; // from resources\img folder
    protected BufferedImage img;
    protected double scaleFactor;

    public void initializeEntityImgSettings(String filePath, int ScaleFactor) {
        this.filePath = filePath; this.scaleFactor = ScaleFactor;
        img = Loader.GetSprite(filePath);
    }

    public Dimension getImgSize() { return new Dimension(img.getWidth(), img.getHeight()); }
    public Vector2<Double> getPosition() { return position; }
    public double getVelocity() { return velocity; }
    public double getScaleFactor() { return scaleFactor; }

    public void drawImg(Graphics g) {
        double scaledWidth = img.getWidth() * scaleFactor;
        double scaledHeight = img.getHeight() * scaleFactor;
        g.drawImage(img, (int)(position.getX() - 0), (int)(position.getY() - 0), (int)scaledWidth, (int)scaledHeight, null);
    }

    public void setVelocity(double velocity) { this.velocity = velocity; }

    public abstract void move();
    public abstract void update();
    public abstract void draw(Graphics g);
}