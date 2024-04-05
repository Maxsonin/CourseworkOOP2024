package Entitys;

import map.Map;
import utils.Loader;
import utils.Point;

import java.awt.*;

public class NaziAttacker extends Attacker {
    public NaziAttacker(Map map, Point<Double> position) {
        super(map, position);
        InitializeImgSettings("nazi.png", new Point<Integer>(180, 260),new Point<Integer>(0, 0), 3);
        InitializeStats(0.3, 20, 15);

        this.img = Loader.GetSprite(fileName);
        this.croppedImg = img.getSubimage(subImgPos.getX(), subImgPos.getY(), imgSize.getX(), imgSize.getY());
    }

    @Override
    public void Move() {
        position.moveX(velocity);
    }

    @Override
    public void BaseAttack() {

    }

    @Override
    public void Draw(Graphics g) {
        int scaledWidth = imgSize.getX() / scaleDividerFactor;
        int scaledHeight = imgSize.getY() / scaleDividerFactor;
        g.drawImage(croppedImg, (int)(position.getX() - 0), (int)(position.getY() - 0), scaledWidth, scaledHeight, null);
    }

    @Override
    public void Update() {
       Move();
    }
}
