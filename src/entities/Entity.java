package entities;

import main.GameWorld;
import utils.Loader;
import utils.Vector2;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public abstract class Entity implements Cloneable {
    // Positioning
    protected double velocity;
    protected Vector2<Double> position;

    // Image
    protected transient String filePath; // from resources\img folder
    protected transient BufferedImage img; // Marked as transient
    protected double scaleFactor;

    public void initializeEntityImgSettings(String filePath, double scaleFactor) {
        this.filePath = filePath;
        this.scaleFactor = scaleFactor;
        img = Loader.GetSprite(filePath); // Load image using your Loader class
    }

    public void setImg(BufferedImage newImg) {
        img = newImg;
    }

    public Dimension getImgSize() {
        return new Dimension(img.getWidth(), img.getHeight());
    }

    public Vector2<Double> getPosition() {
        return position;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getScaleFactor() {
        return scaleFactor;
    }

    public void drawImg(Graphics g) {
        double scaledWidth = img.getWidth() * scaleFactor;
        double scaledHeight = img.getHeight() * scaleFactor;
        g.drawImage(img, (int) (position.getX() - 0), (int) (position.getY() - 0), (int) scaledWidth, (int) scaledHeight, null);
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public abstract void move(GameWorld gameWorld);

    public abstract void update(GameWorld gameWorld);

    public abstract void draw(Graphics g);

    public Entity deepCopy() {
        try {
            Entity copy = (Entity) super.clone(); // Perform shallow copy
            copy.position = new Vector2<>(this.position.getX(), this.position.getY()); // Deep copy of position
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

        BufferedImage copy = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB); // Ensure alpha channel
        Graphics2D g = copy.createGraphics();
        g.drawImage(original, 0, 0, width, height, null);
        g.dispose();

        return copy;
    }

    // Custom serialization logic
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject(); // Serialize other fields automatically
        if (img != null) {
            ImageIO.write(img, "png", out); // Write BufferedImage as PNG to ObjectOutputStream
        }
    }

    // Custom deserialization logic
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject(); // Deserialize other fields automatically
        img = ImageIO.read(in); // Read BufferedImage from ObjectInputStream

        // Ensure the image type supports transparency
        if (img != null) {
            BufferedImage compatibleImg = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = compatibleImg.createGraphics();
            g.drawImage(img, 0, 0, null);
            g.dispose();
            img = compatibleImg;
        }
    }

    @Override
    public String toString() {
        return "Entity{" +
                "position=" + position +
                '}';
    }
}
