package main;

import bases.Base;
import bases.CapturePoint;
import bases.NaziBase;
import bases.SovietBase;
import entities.base.Infantry;
import entities.nazi.NaziInfantry;
import entities.nazi.NaziKombat;
import entities.nazi.NaziSquadLeader;
import entities.soviets.SovietInfantry;
import entities.soviets.SovietKombat;
import entities.soviets.SovietSquadLeader;
import utils.SD;
import utils.Vector2;
import map.Map;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameWorld
{
    // Main Fields
    private Map map = new Map();

    private ArrayList<Base> bases = new ArrayList<>();
    private ArrayList<Infantry> entities = new ArrayList<>();

    // Initializers
    public GameWorld() {
        initializeBases();
        initializeEntities();
    }
    private void initializeEntities() {
        // Nazi
        entities.add(new NaziKombat(new Vector2<>(200.0, 100.0)));
        entities.add(new NaziSquadLeader(new Vector2<>(200.0, 300.0)));
        entities.add(new NaziInfantry(new Vector2<>(200.0, 500.0)));

        // Soviet
        entities.add(new SovietKombat(new Vector2<>(1300.0, 100.0)));
        entities.add(new SovietSquadLeader(new Vector2<>(1300.0, 300.0)));
        entities.add(new SovietInfantry(new Vector2<>(1000.0, 200.0)));
    }
    private void initializeBases() {
        // Nazi
        bases.add(createNaziBase(new Vector2<>(150.0, 1100.0), Color.BLUE, SD.WerwolfNaziBase));
        bases.add(createNaziBase(new Vector2<>(1100.0, 450.0), Color.RED, SD.KrasnohradNaziBase));
        bases.add(createNaziBase(new Vector2<>(1000.0, 1600.0), Color.YELLOW, SD.YaltaNaziBase));

        // Soviet
        bases.add(createSovietBase(new Vector2<>(2900.0, 500.0), Color.GREEN, SD.StalingradSovietBase));
        bases.add(createSovietBase(new Vector2<>(2850.0, 1000.0), Color.MAGENTA, SD.ElistaSovietBase));
        bases.add(createSovietBase(new Vector2<>(2600.0, 1400.0), Color.CYAN, SD.KubanSovietBase));

        // Capture Points
        bases.add(createCapturePoint(new Vector2<>(1900.0, 600.0), SD.DonetskCapturePointImgFile, Color.BLACK, SD.DonetskCapturePoint));
        bases.add(createCapturePoint(new Vector2<>(1800.0, 900.0), SD.RostovCapturePointImgFile, Color.ORANGE, SD.RostovCapturePoint));
        bases.add(createCapturePoint(new Vector2<>(2000.0, 1600.0), SD.MaikopCapturePointImgFile, Color.PINK, SD.MaikopCapturePoint));
    }

    // Getters&Setters
    public Map getMap() { return map; }
    public void setBases(ArrayList<Base> bases) { this.bases = bases; }
    public void setEntities(ArrayList<Infantry> entities) { this.entities = entities; }
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
    public Base getBaseByName(String baseName) {
        for (Base base : bases) {
            if (base.getName().equalsIgnoreCase(baseName)) {
                return base;
            }
        }
        return null;
    }

    // Helper Methods
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

    // Methods
    private void manageEntitiesToBeAddedOrRemovedFromBases() { // Requirement №36
        for (Base base : bases) {
            for (Infantry entity : entities) {
                if (!entity.isNeedToGoToTargetBase() && !base.getEntities().contains(entity)) { // Requirement №37 (Retreating they will skip all bases to fast changes on the field)
                    if (base.isEntityInsideBase(entity) && base.getEntities().size() < base.getBaseCapacity()) { // Requirement №15
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
    private void checkIfAnyEntityIsDeadAndRemoveIt() {
        Iterator<Infantry> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Infantry entity = iterator.next();
            if (entity.getHP() <= 0) {
                iterator.remove(); // Safe removal using iterator
            }
        }
    }

    public void moveEntitiesToMainBase() {
        for (var entity : entities) {
            entity.setNeedToGoToTargetBase(!entity.isNeedToGoToTargetBase());
            if (entity.isNeedToGoToTargetBase()) {
                if (entity.getClass().toString().contains(SD.Soviet)) {
                    entity.setTarget(getBaseByName(SD.MainSovietBase));
                }
                else if (entity.getClass().toString().contains(SD.Nazi)) {
                    entity.setTarget(getBaseByName(SD.MainNaziBase));
                }
            }
            else {
                Random random = new Random();
                entity.setTarget(bases.get(random.nextInt(bases.size())));
                entity.setNeedToAttack(true);
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

    public void render(Graphics g) { // Requirement №10
        map.draw(g);

        for (var base : bases) {
            base.draw(g);
        }

        for (var n : entities) {
            n.draw(g);
        }
    }

    public void update() {
        map.update();

        checkIfAnyEntityIsDeadAndRemoveIt();

        manageEntitiesToBeAddedOrRemovedFromBases();

        for (var entity : entities) {
            entity.update(this);
        }

        for (var base : bases) {
            base.update();
        }
    }

    // Map Related Methods
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