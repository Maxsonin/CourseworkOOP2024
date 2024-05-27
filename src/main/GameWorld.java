package main;

import bases.Base;
import bases.NaziBase;
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

public class GameWorld
{
    // Map Settings
    private Map map;

    private ArrayList<Base> bases = new ArrayList<>();
    private ArrayList<Infantry> entities = new ArrayList<>();

    public GameWorld() {
        initBases();
        InnitEntities();
    }

    public void initBases() {
        // Macro Objects Initialization
        bases.add(createNaziBase(new Vector2<>(200.0, 100.0), Color.BLUE, "Werwolf Nazi Base"));
        bases.add(createNaziBase(new Vector2<>(200.0, 300.0), Color.RED, "Secret Nazi Base in Krasnohrad"));
        bases.add(createNaziBase(new Vector2<>(200.0, 500.0), Color.YELLOW, "Yalta \"DZIDZIO\" Nazi Base"));
    }
    private NaziBase createNaziBase(Vector2<Double> position, Color color, String name) {
        NaziBase base = new NaziBase(position);
        base.InitializeBaseSettings(color, name);
        return base;
    }

    public void InnitEntities() {
        entities.add(new NaziKombat(new Vector2<>(100.0, 100.0)));
        entities.add(new NaziSquadLeader(new Vector2<>(100.0, 300.0)));
        entities.add(new NaziInfantry(new Vector2<>(100.0, 500.0)));

        entities.add(new SovietKombat(new Vector2<>(500.0, 100.0)));
        entities.add(new SovietSquadLeader(new Vector2<>(500.0, 300.0)));
        entities.add(new SovietInfantry(new Vector2<>(500.0, 500.0)));
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

    public ArrayList<Infantry> getAllControllableEntities() {
        ArrayList<Infantry> allControllableEntities = new ArrayList<>();

        for (Infantry entity : entities) {
            if (entity.getControllableComponent().isControllable()) {
                allControllableEntities.add(entity);
            }
        }

        return allControllableEntities;
    }

    public void Render(Graphics g) {
        //Map.Draw(g);
        for (var base : bases) {
            base.draw(g);
        }

        for (var n : entities) {
            n.draw(g);
        }
    }

    public void Update() {
        //Map.Update();
        manageEntitiesToBases();

        for (var n : entities) {
            n.changeHealthColor(Color.green);
            n.update();
        }

        for (var base : bases) {
            base.update();
        }


    }
}