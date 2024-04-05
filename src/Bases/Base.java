package Bases;

import javafx.scene.text.Text;
import utils.Point;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class Base {
    protected String fileName;
    protected BufferedImage img;
    protected Point<Integer> imgSize;

    protected Color baseColor;
    protected String baseName;

    protected Point<Double> position;

    public Base(Point<Double> position) {
        this.position = position;
    }

    public void InitializeImg(String fileName, Point<Integer> imgSize) {
        this.fileName = fileName;
        this.imgSize = imgSize;
    }

    public void InitializeBaseSettings(Color baseColor, String baseName) {
        this.baseColor = baseColor;
        this.baseName = baseName;
    }

    public abstract void AddEntity();
    public abstract void Draw(Graphics g);
    public abstract void Update();
}