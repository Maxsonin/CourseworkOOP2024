package bases;

import entitys.base.Infantry;
import utils.Loader;
import utils.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class CapturePoint extends Base {
    public CapturePoint(Vector2<Double> position, String fileName) {
        super(position);
        InitializeImg(fileName, 0.7);
        img = Loader.GetSprite(fileName);

        entities = new ArrayList<>();
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
        for (var entity : entities) {
            entity.changeHealthColor(color);
        }
    }
}
