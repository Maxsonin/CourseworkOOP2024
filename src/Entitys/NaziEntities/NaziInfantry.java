package Entitys.NaziEntities;

import Entitys.BaseClasses.Infantry;
import Utils.Vector2;

import java.awt.*;

public class NaziInfantry extends Infantry {
    public NaziInfantry(Vector2<Double> position) {
        super(position);
        initializeEntityImgSettings("nazi/entities/infantry.png", 1);
        initializeBaseStats(0.3, 20, 15);
    }

    @Override
    public void move() {
        position.moveX(velocity);
    }

    @Override
    public void update() {
        if (!getControllableComponent().isControllable()) {
            move();
        }
    }

    @Override
    public void draw(Graphics g) {
        drawImg(g);

        if (getControllableComponent().isControllable()) {
            controllable.drawBorder(g);
        }
    }

    @Override
    public void baseAttack() {

    }
}
