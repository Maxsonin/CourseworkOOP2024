package Entitys;

import java.awt.*;
import utils.Point;

public abstract class Entity {
    protected double velocity;

    protected Point<Double> position;

    public Entity(Point<Double> position) {
        this.position = position;
    }

    public void InitializeStats(double velocity) {
        this.velocity = velocity;
    }

    public abstract void Move();

    public abstract void Draw(Graphics g);

    public abstract void Update();
}