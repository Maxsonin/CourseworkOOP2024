package entitys.NaziEntities;

import entitys.BaseClasses.SquadLeader;
import utils.Vector2;

import java.awt.*;

public class NaziSquadLeader extends SquadLeader {
    public NaziSquadLeader(Vector2<Double> position) {
        super(position);
        initializeEntityImgSettings("nazi/entities/squadLeader.png", 1);
        initializeBaseStats(0.3, 20, 15);
        initializeSquadLeaderStats(20, 25);
    }

    public NaziSquadLeader(String id, boolean isControllable, Vector2<Double> position, double velocity, int damage) {
        super(position);
        this.setID(id);
        initializeEntityImgSettings("nazi/entities/squadLeader.png", 1);
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
