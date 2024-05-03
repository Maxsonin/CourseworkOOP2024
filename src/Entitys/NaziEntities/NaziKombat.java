package Entitys.NaziEntities;

import Entitys.BaseClasses.Kombat;
import Utils.Vector2;

import java.awt.*;

public class NaziKombat extends Kombat {

    public NaziKombat(Vector2<Double> position) {
        super(position);
        initializeEntityImgSettings("nazi/entities/kombat.png", 1);
        initializeBaseStats(0.3, 20, 15);
        initializeSquadLeaderStats(20, 25);
        initializeKombatStats(10, 25);
    }

    @Override
    public void move() {
        position.moveX(velocity);
    }

    @Override
    public void draw(Graphics g) {
        drawImg(g);

        if (getControllableComponent().isControllable()) {
            controllable.drawBorder(g);
        }
    }

    @Override
    public void update() {
        if (!getControllableComponent().isControllable()) {
            move();
        }
    }

    @Override
    public void baseAttack() {

    }

    @Override
    public void squadLeaderAttack() {

    }

    @Override
    public void kombatAttack() {

    }
}
