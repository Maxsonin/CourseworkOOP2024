package bases;

import entitys.base.Infantry;
import utils.Vector2;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class Base {
    // Img Settings
    protected String fileName;
    protected BufferedImage img;

    // Base Identity Settings
    protected Color color;
    protected String name;
    protected double scaleFactor;

    // Base Setting
    protected Vector2<Double> position;
    protected Vector2<Double> entitySpawnPos;
    protected ArrayList<Infantry> entities;
    protected int baseCapacity;

    public Base(Vector2<Double> position) {
        this.position = position;
        entitySpawnPos = new Vector2<>(position.getX() + 50,position.getY() + 50); // random values of base center
    }

    public void InitializeImg(String fileName, double scaleFactor) {
        this.fileName = fileName;
        this.scaleFactor = scaleFactor;
    }

    public void InitializeBaseSettings(Color baseColor, String baseName) {
        this.color = baseColor; this.name = baseName;
    }

    public String getName() { return name; }
    public Vector2<Double> getEntitySpawnPos() { return entitySpawnPos; }
    public ArrayList<Infantry> getEntities() { return entities; }
    public Vector2<Double> getPosition() { return position; }
    public int getBaseCapacity() {
        return baseCapacity;
    }

    public void drawImg(Graphics g) {
        double scaledWidth = img.getWidth() * scaleFactor;
        double scaledHeight = img.getHeight() * scaleFactor;
        g.drawImage(img, (int)(position.getX() - 0), (int)(position.getY() - 0), (int)scaledWidth, (int)scaledHeight, null);
    }

    public boolean isEntityInsideBase(Infantry entity) {
        Vector2<Double> entityPos = entity.getPosition();
        double x = entityPos.getX();
        double y = entityPos.getY();

        return x >= position.getX() && x <= position.getX() + img.getWidth() * scaleFactor &&
                y >= position.getY() && y <= position.getY() + img.getHeight() * scaleFactor;
    }

    public abstract void draw(Graphics g);
    public abstract void update();
}