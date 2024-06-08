package main;

import bases.Base;
import bases.CapturePoint;
import bases.NaziBase;
import bases.SovietBase;
import entitys.base.Infantry;
import entitys.nazi.NaziInfantry;
import entitys.nazi.NaziKombat;
import entitys.nazi.NaziSquadLeader;
import entitys.soviets.SovietInfantry;
import entitys.soviets.SovietKombat;
import entitys.soviets.SovietSquadLeader;
import utils.SD;
import utils.Vector2;
import map.Map;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class GameWorld
{
    private Map map = new Map();;

    private ArrayList<Base> bases = new ArrayList<>();
    private ArrayList<Infantry> entities = new ArrayList<>();

    public GameWorld() {
        initBases();
        InnitEntities();
    }

    public void InnitEntities() {
        // Nazi
        entities.add(new NaziKombat(new Vector2<>(200.0, 100.0)));
        entities.add(new NaziSquadLeader(new Vector2<>(200.0, 300.0)));
        entities.add(new NaziInfantry(new Vector2<>(200.0, 500.0)));

        // Soviet
        entities.add(new SovietKombat(new Vector2<>(1300.0, 100.0)));
        entities.add(new SovietSquadLeader(new Vector2<>(1300.0, 300.0)));
        entities.add(new SovietInfantry(new Vector2<>(1300.0, 500.0)));
    }

    public void initBases() {
        // Nazi
        bases.add(createNaziBase(new Vector2<>(150.0, 400.0), Color.BLUE, SD.WerwolfNaziBase));
        bases.add(createNaziBase(new Vector2<>(500.0, 250.0), Color.RED, SD.KrasnohradNaziBase));
        bases.add(createNaziBase(new Vector2<>(400.0, 800.0), Color.YELLOW, SD.YaltaNaziBase));

        // Soviet
        bases.add(createSovietBase(new Vector2<>(1250.0, 200.0), Color.GREEN, SD.StalingradSovietBase));
        bases.add(createSovietBase(new Vector2<>(1350.0, 500.0), Color.MAGENTA, SD.ElistaSovietBase));
        bases.add(createSovietBase(new Vector2<>(1200.0, 800.0), Color.CYAN, SD.KubanSovietBase));

        // Capture Points
        bases.add(createCapturePoint(new Vector2<>(900.0, 300.0), SD.DonetskCapturePointImgFile, Color.BLACK, SD.DonetskCapturePoint));
        bases.add(createCapturePoint(new Vector2<>(800.0, 500.0), SD.RostovCapturePointImgFile, Color.ORANGE, SD.RostovCapturePoint));
        bases.add(createCapturePoint(new Vector2<>(950.0, 750.0), SD.MaikopCapturePointImgFile, Color.PINK, SD.MaikopCapturePoint));
    }
    private NaziBase createNaziBase(Vector2<Double> position, Color color, String name) {
        NaziBase base = new NaziBase(position);
        base.InitializeBaseSettings(color, name);
        return base;
    }
    private SovietBase createSovietBase(Vector2<Double> position, Color color, String name) {
        SovietBase base = new SovietBase(position);
        base.InitializeBaseSettings(color, name);
        return base;
    }
    private CapturePoint createCapturePoint(Vector2<Double> position, String img, Color color, String name) {
        CapturePoint base = new CapturePoint(position, img);
        base.InitializeBaseSettings(color, name);
        return base;
    }

    public Base getBaseByName(String baseName) {
        for (Base base : bases) {
            if (base.getName().equalsIgnoreCase(baseName)) {
                return base;
            }
        }
        return null;
    }

    public void manageEntitiesToBases() {
        for (Base base : bases) {
            for (Infantry entity : entities) {
                if (!base.getEntities().contains(entity)) {
                    if (base.isEntityInsideBase(entity)) {
                        base.getEntities().add(entity);
                    }
                } else {
                    if (!base.isEntityInsideBase(entity)) {
                        base.getEntities().remove(entity);
                    }
                }
            }
        }
    }

    public void addCustomSovietEntity(String id, boolean isControllable, Vector2<Double> position, String selectedClass, double velocity, int damage) {
        switch (selectedClass) {
            case SD.Infantry:
                entities.add(new SovietInfantry(id, isControllable, position, velocity, damage));
                break;
            case SD.SquadLeader:
                entities.add(new SovietSquadLeader(id, isControllable, position, velocity, damage));
                break;
            case SD.Kombat:
                entities.add(new SovietKombat(id, isControllable, position, velocity, damage));
                break;
            default:
                throw new IllegalArgumentException("Unknown entity class");
        }
    }
    public void addCustomNaziEntity(String id, boolean isControllable, Vector2<Double> position, String selectedClass, double velocity, int damage) {
        switch (selectedClass) {
            case SD.Infantry:
                entities.add(new NaziInfantry(id, isControllable, position, velocity, damage));
                break;
            case SD.SquadLeader:
                entities.add(new NaziSquadLeader(id, isControllable, position, velocity, damage));
                break;
            case SD.Kombat:
                entities.add(new NaziKombat(id, isControllable, position, velocity, damage));
                break;
            default:
                throw new IllegalArgumentException("Unknown entity class");
        }
    }

    public Map getMap() { return map; }
    public ArrayList<Base> getBases() { return bases; }
    public ArrayList<Infantry> getEntities() { return entities; }
    public ArrayList<Infantry> getEntitiesThatAreInBase() {
        ArrayList<Infantry> entitiesThatAreInBase = new ArrayList<>();
        for (Base base : bases) {
            entitiesThatAreInBase.addAll(base.getEntities());
        }
        return entitiesThatAreInBase;
    }
    public ArrayList<Infantry> getEntitiesThatAreNOTInBase() {
        ArrayList<Infantry> entitiesThatAreInBase = getEntitiesThatAreInBase();
        ArrayList<Infantry> entitiesNotInBase = new ArrayList<>(entities);
        entitiesNotInBase.removeAll(entitiesThatAreInBase);
        return entitiesNotInBase;
    }
    public ArrayList<Infantry> getAllControllableEntities() {
        ArrayList<Infantry> allControllableEntities = new ArrayList<>();

        for (Infantry entity : entities) {
            if (entity.getControllableComponent().isControllable()) {
                allControllableEntities.add(entity);
            }
        }

        return allControllableEntities;
    }

    private void checkBodies() {
        Iterator<Infantry> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Infantry entity = iterator.next();
            if (entity.getHP() <= 0) {
                iterator.remove(); // Safe removal using iterator
            }
        }
    }

    public void Render(Graphics g) {
        map.draw(g);
        for (var base : bases) {
            base.draw(g);
        }

        for (var n : entities) {
            n.draw(g);
        }
    }

    public void Update() {
        map.update();

        checkBodies();
        manageEntitiesToBases();

        for (var n : entities) {
            n.changeHealthColor(Color.green);
            n.update(this);
        }

        for (var base : bases) {
            base.update();
        }
    }

    public void moveDown(double amount) {
        for (var base : bases) {
            base.getPosition().moveY(-amount);
        }

        for (var entity : entities) {
            entity.getPosition().moveY(-amount);
        }
    }

    public void moveRight(double amount) {
        for (var base : bases) {
            base.getPosition().moveX(-amount);
        }

        for (var entity : entities) {
            entity.getPosition().moveX(-amount);
        }
    }

    public void moveLeft(double amount) {
        for (var base : bases) {
            base.getPosition().moveX(amount);
        }

        for (var entity : entities) {
            entity.getPosition().moveX(amount);
        }
    }

    public void moveUp(double amount) {
        for (var base : bases) {
            base.getPosition().moveY(amount);
        }

        for (var entity : entities) {
            entity.getPosition().moveY(amount);
        }
    }
}