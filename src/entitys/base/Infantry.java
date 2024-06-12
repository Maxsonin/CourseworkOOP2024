// Firs Entity in the triple inheritance hierarchy

package entitys.base;

import bases.Base;
import components.Controllable;
import components.HealthStats;
import entitys.Entity;
import main.GameWorld;
import utils.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public abstract class Infantry extends Entity {
    // Main Fields
        // Entity movement
        protected boolean needToAttack = false;
        protected boolean needToGoToTargetBase = false;
        protected Base target;

        // Components
        private Controllable controllableComponent; // Requirement №6
        protected HealthStats healthStatsComponent; // Requirement №6

        // Entity features
        protected int damage;
        protected int sightRadius;
        protected int maxHealth = 100;
        protected long lastDamageTime;
        protected int timeForReload;
        protected String ID;

    // Initializers
    public Infantry() {  // Requirement №2
        initializeComponents();
        generateID();
    }
    public Infantry(Vector2<Double> spawnPosition) {
        this(); this.position = spawnPosition;  // Requirement №3
    }

    private void initializeComponents() {
        controllableComponent = new Controllable(this);
        healthStatsComponent = new HealthStats(this, maxHealth);
    }
    private void generateID() {
        Random random = new Random();
        int randomNumber = random.nextInt(10000); // 0-9999
        ID = String.format("%04d", randomNumber);
    }
    public void initializeBaseStats(double velocity, int damage, int sightRadius, int timeForReload) {
        this.velocity = velocity;
        this.damage = damage;
        this.sightRadius = sightRadius;
        this.timeForReload = timeForReload;
    }

    // Getters&Setters
    public String getID() { return ID; }
    public int getDamage() { return damage; }
    public int getHP() { return healthStatsComponent.getHealth(); }
    public void setDamage(int damage) { this.damage = damage; }
    public void setID(String ID) { this.ID = ID; }
    public void setNeedToGoToTargetBase(boolean needToGoToTargetBase) {
        this.needToGoToTargetBase = needToGoToTargetBase;
    }
    public boolean isNeedToGoToTargetBase() {
        return needToGoToTargetBase;
    }
    public Base getTarget() {
        return target;
    }
    public void setTarget(Base target) {
        this.target = target;
    }
    public Controllable getControllableComponent() { return controllableComponent; }
    public HealthStats getHealthStatsComponent() { return healthStatsComponent; }

    // Methods
    public void moveToTargetBase() {
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

            if (magnitude != 0 && magnitude >= 1) { // include measurement error
                // Update the entity's position
                position.setX(position.getX() + moveX);
                position.setY(position.getY() + moveY);
            }
    }

    public ArrayList<Infantry> getEntitiesWithinSightRadius(ArrayList<Infantry> allEntities) {
        ArrayList<Infantry> entitiesInRange = new ArrayList<>();
        for (Infantry entity : allEntities) {
            if (entity != this) { // Don't include the entity itself
                double distance = position.distanceTo(entity.getPosition());
                if (distance <= sightRadius) {
                    entitiesInRange.add(entity);
                }
            }
        }
        return entitiesInRange;
    }

    public void drawSightRadius(Graphics g) {
        g.setColor(new Color(255, 0, 0, 25));
        int diameter = sightRadius * 2;
        int halfOfImgW = (int)(img.getWidth() * scaleFactor / 2);
        int halfOfImgH = (int)(img.getHeight() * scaleFactor / 2);
        g.fillOval((int)(position.getX() + halfOfImgW - sightRadius), (int)(position.getY() + halfOfImgH - sightRadius), diameter, diameter);
    }

    public void Shoot(GameWorld gameWorld, String enemyTeam) {
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastDamageTime >= timeForReload) {
            ArrayList<Infantry> entitiesInRange = getEntitiesWithinSightRadius(gameWorld.getEntities());
            for (var entity : entitiesInRange) {
                if (entity.getClass().toString().contains(enemyTeam)) {
                    entity.getHealthStatsComponent().changeHealth(-damage);
                }
            }
            lastDamageTime = currentTime;
        }
    }

    @Override
    public Infantry deepCopy() { // Requirement №7
        Infantry copy = (Infantry) super.deepCopy(); // Shallow copy of Entity
        copy.controllableComponent = new Controllable(copy); // Deep copy of Controllable
        copy.controllableComponent.setControllable(false);
        copy.healthStatsComponent = new HealthStats(copy, this.healthStatsComponent.getHealth()); // Deep copy of HealthStats

        return copy;
    }

    @Override
    public String toString() {
        return  "ID = " + ID + "\n" +
                "Damage = " + damage + "\n" +
                "Health = " + getHP() + "\n" +
                "Position = " + position.toString() + "\n";
    }
}