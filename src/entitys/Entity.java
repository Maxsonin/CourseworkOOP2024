// Base Entity class which sets main properties for every entity in the Game

package entitys;

import java.awt.*;
import java.awt.image.BufferedImage;

import entitys.BaseClasses.Infantry;
import utils.Loader;
import utils.Vector2;

public abstract class Entity implements Cloneable  {
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

    public Entity deepCopy() {
        try {
            Entity copy = (Entity) super.clone(); // Perform shallow copy
            copy.position = new Vector2<>(this.position.getX() - 100, this.position.getY()); // Deep copy of position
            copy.img = deepCopyImage(this.img); // Deep copy of BufferedImage
            return copy;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    // Helper method to deep copy BufferedImage
    private BufferedImage deepCopyImage(BufferedImage original) {
        if (original == null) {
            return null;
        }

        int width = original.getWidth();
        int height = original.getHeight();

        BufferedImage copy = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = copy.createGraphics();

        try {
            g.drawImage(original, 0, 0, width, height, null);
            return copy;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            g.dispose();
        }
    }
}