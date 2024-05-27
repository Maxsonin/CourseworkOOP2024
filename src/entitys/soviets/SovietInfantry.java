package entitys.soviets;

import entitys.base.Infantry;
import utils.Vector2;

import java.awt.*;

public class SovietInfantry extends Infantry {
    public SovietInfantry(Vector2<Double> position) {
        super(position);
        initializeEntityImgSettings("soviet/entities/infantry.png", 0.7);
        initializeBaseStats(-0.5, 20, 15);
    }

    public SovietInfantry(String id, boolean isControllable, Vector2<Double> position, double velocity, int damage) {
        super(position);
        this.setID(id);
        initializeEntityImgSettings("soviet/entities/infantry.png", 0.7);
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
