package Entitys;

import map.Map;
import utils.Point;

import java.awt.image.BufferedImage;

public abstract class Attacker extends Entity {
    protected Map map;

    protected String fileName;
    protected BufferedImage img;
    protected BufferedImage croppedImg;

    protected Point<Integer> imgSize;
    protected Point<Integer> subImgPos;
    protected int scaleDividerFactor;

    protected int damage;
    protected int sightRadius;

    public void InitializeImgSettings(String fileName, Point<Integer> imgSize, Point<Integer> subImgPos, int ScaleDividerFactor) {
        this.fileName = fileName;
        this.imgSize = imgSize;
        this.subImgPos = subImgPos;
        this.scaleDividerFactor = ScaleDividerFactor;
    }

    public void InitializeStats(double velocity, int damage, int sightRadius) {
        InitializeStats(velocity);
        this.damage = damage;
        this.sightRadius = sightRadius;
    }

    public Attacker(Map map, Point<Double> position) {
        super(position);
        this.map = map;
    }

    public abstract void BaseAttack();
}