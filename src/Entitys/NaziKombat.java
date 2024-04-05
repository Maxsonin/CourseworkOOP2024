package Entitys;

import map.Map;
import utils.Loader;
import utils.Point;

import java.awt.*;

public class NaziKombat extends Kombat{

    public NaziKombat(Map map, Point<Double> position) {
        super(map, position);
        InitializeImgSettings("nazi.png", new Point<Integer>(137, 260),new Point<Integer>(330, 0), 3);
        InitializeStats(0.1f, 25, 15, 50, 10);

        this.img = Loader.GetSprite(fileName);
        this.croppedImg = img.getSubimage(subImgPos.getX(), subImgPos.getY(), imgSize.getX(), imgSize.getY());
    }

    @Override
    public void BaseAttack() {

    }

    @Override
    public void Move() {
        position.moveX(velocity);
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

    @Override
    public void UltraAttack() {

    }

    @Override
    public void MegaAttack() {

    }
}
