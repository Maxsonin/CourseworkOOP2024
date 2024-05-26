package bases;

import entitys.BaseClasses.Infantry;
import entitys.NaziEntities.NaziInfantry;
import entitys.NaziEntities.NaziKombat;
import entitys.NaziEntities.NaziSquadLeader;
import utils.Loader;
import utils.SD;
import utils.Vector2;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class NaziBase extends Base {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    // Army Settings
    private ArrayList<Infantry> entities;

    private final int maxNumOfAttackers = 5;
    private final int maxNumOfSquadLeaders = 1;
    private final int maxNumOfKombats = 1;

    public NaziBase(Vector2<Double> position) {
        super(position);
        InitializeImg("/nazi/bases/naziBase.png", 0.5);
        img = Loader.GetSprite(fileName);
        entitySpawnPos = new Vector2<>(this.position.getX() + img.getWidth() * scaleFactor / 2 , this.position.getY() + img.getHeight() * scaleFactor / 2);

        entities = new ArrayList<>();

        // Schedule the entity addition task
        scheduler.scheduleAtFixedRate(this::addEntity, 0, 2500, TimeUnit.MILLISECONDS);
    }

    public ArrayList<Infantry> getEntities() { return entities; }

    @Override
    public synchronized void addEntity() {
        if (entities.size() < maxNumOfKombats) {
            entities.add(new NaziKombat(entitySpawnPos.clone()));
        } else if (entities.size() < maxNumOfSquadLeaders + maxNumOfKombats) {
            entities.add(new NaziSquadLeader(entitySpawnPos.clone()));
        } else if (entities.size() < maxNumOfAttackers + maxNumOfSquadLeaders + maxNumOfKombats) {
            entities.add(new NaziInfantry(entitySpawnPos.clone()));
        }
    }

    public void addCustomEntity(String id, boolean isControllable, String selectedClass, double velocity, int damage) {
        switch (selectedClass) {
            case SD.Infantry:
                entities.add(new NaziInfantry(id, isControllable, entitySpawnPos.clone(), velocity, damage));
                break;
            case SD.SquadLeader:
                entities.add(new NaziSquadLeader(id, isControllable, entitySpawnPos.clone(), velocity, damage));
                break;
            case SD.Kombat:
                entities.add(new NaziKombat(id, isControllable, entitySpawnPos.clone(), velocity, damage));
                break;
            default:
                throw new IllegalArgumentException("Unknown entity class");
        }
    }

    public <T extends Infantry> ArrayList<T> getEntitiesByType(Class<T> entityType) {
        ArrayList<T> entitiesOfType = new ArrayList<>();
        for (Infantry entity : entities) {
            if (entityType.isInstance(entity)) {
                entitiesOfType.add(entityType.cast(entity));
            }
        }
        return entitiesOfType;
    }

    @Override
    public void draw(Graphics g) {
        drawImg(g);

        for (var entity : entities) {
            entity.draw(g);
        }

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
            entity.update();
        }
    }

    // Should be called at the end of the application...
    public void shutdown() {
        scheduler.shutdown();
    }
}