package entitys.soviets;

import entitys.base.SquadLeader;
import utils.Vector2;

import java.awt.*;

public class SovietSquadLeader extends SquadLeader {
    public SovietSquadLeader(Vector2<Double> position) {
        super(position);
        initializeEntityImgSettings("soviet/entities/squadLeader.png", 0.7);
        initializeBaseStats(-0.3, 20, 15);
        initializeSquadLeaderStats(20, 25);
    }

    public SovietSquadLeader(String id, boolean isControllable, Vector2<Double> position, double velocity, int damage) {
        super(position);
        this.setID(id);
        initializeEntityImgSettings("soviet/entities/squadLeader.png", 0.7);
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
        // Implement base attack logic
    }

    @Override
    public void squadLeaderAttack() {
        // Implement mega attack logic
    }
}
