package entitys.soviets;

import entitys.base.Kombat;
import utils.Vector2;

import java.awt.*;

public class SovietKombat extends Kombat {

    public SovietKombat(Vector2<Double> position) {
        super(position);
        initializeEntityImgSettings("soviet/entities/kombat.png", 0.7);
        initializeBaseStats(-0.1, 20, 15);
        initializeSquadLeaderStats(20, 25);
        initializeKombatStats(10, 25);
    }

    public SovietKombat(String id, boolean isControllable, Vector2<Double> position, double velocity, int damage) {
        super(position);
        this.setID(id);
        initializeEntityImgSettings("soviet/entities/kombat.png", 0.7);
        initializeBaseStats(velocity, damage, 15);
        controllable.setControllable(isControllable);
    }

    @Override
    public void move() {
        position.moveX(velocity);
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
