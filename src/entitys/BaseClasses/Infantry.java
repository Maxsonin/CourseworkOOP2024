// Firs Entity in the triple inheritance hierarchy

package entitys.BaseClasses;

import components.Controllable;
import components.HealthStats;
import entitys.Entity;
import utils.Vector2;

import java.util.Random;

public abstract class Infantry extends Entity {
    // Components
    protected Controllable controllable;
    protected HealthStats healthStats;

    // Entity features
    protected int damage;
    protected int sightRadius;
    protected int maxHealth = 100;

    protected String ID;


    public Infantry(Vector2<Double> spawnPosition) {
        initializeComponents();
        generateID();
        this.position = spawnPosition;
    }

    public String getID() { return ID; }
    public int getDamage() { return damage; }

    public void setDamage(int damage) { this.damage = damage; }
    public void setID(String ID) { this.ID = ID; }

    private void generateID() {
        Random random = new Random();
        int randomNumber = random.nextInt(10000); // 0-9999
        ID = String.format("%04d", randomNumber);
    }

    private void initializeComponents() {
        controllable = new Controllable(this);
        healthStats = new HealthStats(this, maxHealth);
    }

    public void initializeBaseStats(double velocity, int damage, int sightRadius) {
        this.velocity = velocity;
        this.damage = damage;
        this.sightRadius = sightRadius;
    }

    public Controllable getControllableComponent() { return controllable; }

    public abstract void baseAttack();
}