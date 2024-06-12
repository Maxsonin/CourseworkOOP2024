package entitys.nazi;

import bases.Base;
import entitys.base.SquadLeader;
import main.GameWorld;
import utils.SD;
import utils.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class NaziSquadLeader extends SquadLeader {
    public NaziSquadLeader(Vector2<Double> position) {
        super(position);
        initializeEntityImgSettings("nazi/entities/squadLeader.png", 1);
        initializeBaseStats(0.3, 25, 100, 1000);
        initializeSquadLeaderStats(20, 25);
    }

    public NaziSquadLeader(String id, boolean isControllable, Vector2<Double> position, double velocity, int damage) {
        super(position);
        this.setID(id);
        initializeEntityImgSettings("nazi/entities/squadLeader.png", 1);
        initializeBaseStats(velocity, damage, 100, 1000);
        getControllableComponent().setControllable(isControllable);
    }

    @Override
    public void move(GameWorld gameWorld) {
        if (!needToAttack && !needToGoToTargetBase) {
            ArrayList<Base> bases = gameWorld.getBases();
            if (bases.isEmpty()) {
                return;
            }

            // Pick a random base
            Random random = new Random();
            target = bases.get(random.nextInt(bases.size()));
            needToAttack = true;
        }
        else if (needToAttack && !needToGoToTargetBase)
        {
            // Calculate the direction vector
            double directionX = target.getEntitySpawnPos().getX() - position.getX();
            double directionY = target.getEntitySpawnPos().getY() - position.getY();

            // Normalize the direction vector
            double magnitude = Math.sqrt(directionX * directionX + directionY * directionY);
            double unitDirectionX = directionX / magnitude;
            double unitDirectionY = directionY / magnitude;

            // Calculate the movement vector
            double moveX = unitDirectionX * velocity;
            double moveY = unitDirectionY * velocity;

            // Update the entity's position
            position.setX(position.getX() + moveX);
            position.setY(position.getY() + moveY);

            if (magnitude == 0 || magnitude <= 1) { // include measurement error
                needToAttack = false;
            }
        }
        else if (needToGoToTargetBase) {
            moveToTargetBase();
        }
    }

    @Override
    public void draw(Graphics g) {
        drawImg(g);
        healthStatsComponent.drawHealthStats(g);

        if (getControllableComponent().isControllable()) {
            getControllableComponent().drawBorder(g);
        }

        g.setColor(Color.BLACK);
        g.drawString(ID, healthStatsComponent.getBarPosition().getX(), healthStatsComponent.getBarPosition().getY() - 5);

        drawSightRadius(g);
    }

    @Override
    public void update(GameWorld gameWorld) {
        if (!getControllableComponent().isControllable()) {
            move(gameWorld);
        }

        Shoot(gameWorld, SD.Soviet);

        // Set to orignal Values after base modification of entity
        setVelocity(0.3);
        getHealthStatsComponent().setBarColor(Color.green);
    }
}
