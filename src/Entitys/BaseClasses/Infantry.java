// Firs Entity in the triple inheritance hierarchy

package Entitys.BaseClasses;

import Components.Controllable;
import Entitys.Entity;
import Utils.Vector2;

public abstract class Infantry extends Entity {
    // Components
    protected Controllable controllable;

    // Entity features
    protected int damage;
    protected int sightRadius;


    public Infantry(Vector2<Double> spawnPosition) {
        initializeComponents();
        this.position = spawnPosition;
    }

    private void initializeComponents() {
        controllable = new Controllable(this);
    }

    public void initializeBaseStats(double velocity, int damage, int sightRadius) {
        this.velocity = velocity;
        this.damage = damage;
        this.sightRadius = sightRadius;
    }

    public Controllable getControllableComponent() { return controllable; }

    public abstract void baseAttack();
}