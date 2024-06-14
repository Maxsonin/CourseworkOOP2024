package bases;

import serialization.SerializationFile;
import utils.Loader;
import utils.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class NaziBase extends Base {
    private long lastUpdateTime;

    public NaziBase(Vector2<Double> position) {
        super(position);
        InitializeImg("/nazi/bases/base1.png", 0.4);
        img = Loader.GetSprite(fileName);
        baseCapacity = 30;
        entities = new ArrayList<>();
        lastUpdateTime = System.currentTimeMillis();
    }

    @Override
    public void draw(Graphics g) {
        drawImg(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(8));
        g.setColor(Color.BLACK);
        g.drawOval((int) (position.getX() - 10), (int) (position.getY() - 25), 35, 35);

        g.setColor(color);
        g.fillOval((int) (position.getX() - 10), (int) (position.getY() - 25), 35, 35);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.drawString(name, (int) (position.getX() + 40), (int) (position.getY() + 0));
    }

    @Override
    public void update() {
        entitySpawnPos = new Vector2<>(position.getX() + 50, position.getY() + 50); // random values of base center

        for (var entity : entities) {
            entity.getHealthStatsComponent().setBarColor(color);
            entity.setVelocity(0.1);
        }

        long currentTime = System.currentTimeMillis();
        if (currentTime - lastUpdateTime >= 2000) {
            updateHealth();
            lastUpdateTime = currentTime;
        }
    }

    private void updateHealth() {
        for (var entity : entities) {
            entity.getHealthStatsComponent().changeHealth(+10);
        }
    }
}