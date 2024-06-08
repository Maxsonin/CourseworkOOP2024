package entitys.nazi;

import bases.Base;
import entitys.base.Infantry;
import main.GameWorld;
import utils.SD;
import utils.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class NaziInfantry extends Infantry {
    public NaziInfantry(Vector2<Double> position) {
        super(position);
        initializeEntityImgSettings("nazi/entities/infantry.png", 1);
        initializeBaseStats(0.5, 15, 150, 1000);
    }

    public NaziInfantry(String id, boolean isControllable, Vector2<Double> position, double velocity, int damage) {
        super(position);
        this.setID(id);
        initializeEntityImgSettings("nazi/entities/infantry.png", 1);
        initializeBaseStats(velocity, damage, 150, 1000);
        controllable.setControllable(isControllable);
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
    public void update(GameWorld gameWorld) {
        if (!getControllableComponent().isControllable()) {
            move(gameWorld);
        }

        Shoot(gameWorld, SD.Soviet);
    }

    @Override
    public void draw(Graphics g) {
        drawImg(g);
        healthStats.drawHealthStats(g);

        if (getControllableComponent().isControllable()) {
            controllable.drawBorder(g);
        }

        g.setColor(Color.BLACK);
        g.drawString(ID, healthStats.getBarPosition().getX(), healthStats.getBarPosition().getY() - 5);

        drawSightRadius(g);
    }
}
