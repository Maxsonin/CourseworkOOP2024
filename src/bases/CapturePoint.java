package bases;

import utils.Loader;
import utils.Vector2;

import java.awt.*;
import java.util.ArrayList;

public class CapturePoint extends Base {
    public CapturePoint(Vector2<Double> position, String fileName) {
        super(position);
        InitializeImg(fileName, 1.2);
        img = Loader.GetSprite(fileName);
        baseCapacity = 10;
        entities = new ArrayList<>();
    }

    @Override
    public void draw(Graphics g) {
        drawImg(g); // Requirement №26

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(8));
        g.setColor(Color.BLACK);
        g.drawOval((int) (position.getX() - 10), (int) (position.getY() - 25), 35, 35);

        g.setColor(color);
        g.fillOval((int) (position.getX() - 10), (int) (position.getY() - 25), 35, 35); // Requirement №26

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 22));
        g.drawString(name, (int) (position.getX() + 40), (int) (position.getY() + 0)); // Requirement №26
    }

    @Override
    public void update() {
        entitySpawnPos = new Vector2<>(position.getX() + 50,position.getY() + 50); // random values of base center

        for (var entity : entities) {
            entity.getHealthStatsComponent().setBarColor(color);
            entity.setVelocity(0.1);
        }
    }
}
