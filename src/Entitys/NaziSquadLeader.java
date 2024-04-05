package Entitys;

import map.Map;
import utils.Loader;
import utils.Point;

import java.awt.*;

public class NaziSquadLeader extends SquadLeader {

    public NaziSquadLeader(Map map, Point<Double> position) {
        super(map, position);
        InitializeImgSettings("nazi.png", new utils.Point<Integer>(160, 260),new Point<Integer>(180, 0), 3);
        InitializeStats(0.2f, 25, 15, 50, 10);

        img = Loader.GetSprite(fileName);
        this.croppedImg = img.getSubimage(subImgPos.getX(), subImgPos.getY(), imgSize.getX(), imgSize.getY());
    }

    @Override
    public void Move() {
         position.moveX(velocity);
    }

    @Override
    public void BaseAttack() {
        // Implement base attack logic
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
    public void MegaAttack() {
        // Implement mega attack logic
    }
}
