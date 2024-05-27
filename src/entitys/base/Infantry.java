// Firs Entity in the triple inheritance hierarchy

package entitys.base;

import components.Controllable;
import components.HealthStats;
import entitys.Entity;
import utils.Vector2;

import java.awt.*;
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

    public Infantry() {
        initializeComponents();
        generateID();
    }

    public Infantry(Vector2<Double> spawnPosition) {
        this();
        this.position = spawnPosition;
    }

    public String getID() { return ID; }
    public int getDamage() { return damage; }

    public void setDamage(int damage) { this.damage = damage; }
    public void setID(String ID) { this.ID = ID; }

    // Static initialization block
    static {
        System.out.println("Static block initialized");
    }

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

    @Override
    public Infantry deepCopy() {
        Infantry copy = (Infantry) super.deepCopy(); // Shallow copy of Entity
        copy.controllable = new Controllable(copy); // Deep copy of Controllable
        copy.controllable.setControllable(false);
        copy.healthStats = new HealthStats(copy, this.healthStats.getHealth()); // Deep copy of HealthStats

        return copy;
    }

    public void changeHealthColor(Color color) {
        healthStats.setBarColor(color);
    }
}