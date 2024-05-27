package entitys.nazi;

import entitys.base.Infantry;
import utils.Vector2;

import java.awt.*;

public class NaziInfantry extends Infantry {
    public NaziInfantry(Vector2<Double> position) {
        super(position);
        initializeEntityImgSettings("nazi/entities/infantry.png", 1);
        initializeBaseStats(0.5, 20, 15);
    }

    public NaziInfantry(String id, boolean isControllable, Vector2<Double> position, double velocity, int damage) {
        super(position);
        this.setID(id);
        initializeEntityImgSettings("nazi/entities/infantry.png", 1);
        initializeBaseStats(velocity, damage, 15);
        controllable.setControllable(isControllable);
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
        healthStats.drawHealthStats(g);

        if (getControllableComponent().isControllable()) {
            controllable.drawBorder(g);
        }
    }

    @Override
    public void baseAttack() {

    }
}
