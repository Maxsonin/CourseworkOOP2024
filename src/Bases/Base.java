package Bases;

import Utils.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Base {
    // Img Settings
    protected String fileName;
    protected BufferedImage img;

    // Base Identity Settings
    protected Color color;
    protected String name;
    protected double scaleFactor;

    // Base Setting
    protected final Vector2<Double> position;

    public Base(Vector2<Double> position) { this.position = position; }

    public void InitializeImg(String fileName, double scaleFactor) {
        this.fileName = fileName;
        this.scaleFactor = scaleFactor;
    }

    public void InitializeBaseSettings(Color baseColor, String baseName) {
        this.color = baseColor; this.name = baseName;
    }

    public void drawImg(Graphics g) {
        double scaledWidth = img.getWidth() * scaleFactor;
        double scaledHeight = img.getHeight() * scaleFactor;
        g.drawImage(img, (int)(position.getX() - 0), (int)(position.getY() - 0), (int)scaledWidth, (int)scaledHeight, null);
    }

    public String getName() { return name; }

    public abstract void addEntity();
    public abstract void draw(Graphics g);
    public abstract void update();
}