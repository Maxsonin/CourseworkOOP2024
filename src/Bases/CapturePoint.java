package Bases;

import utils.Loader;
import utils.Point;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CapturePoint {
    private String fileName;
    private BufferedImage img;
    private Point<Integer> imgSize;

    private Point<Double> position;

    public CapturePoint(int position) {

    }

    public void InitializeImg(String fileName) {
        this.fileName = fileName;
        img = Loader.GetSprite(fileName);
        imgSize = new Point<>(860, 539);
    }

    public void Draw(Graphics g) {
        g.drawImage(img, 300, 300, imgSize.getX(), imgSize.getY(), null);
    }

    public void Update() {

    }

    public void CountEntityes() {

    }
}
