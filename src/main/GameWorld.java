package main;

import bases.NaziBase;
import entitys.BaseClasses.Infantry;
import utils.Vector2;
import map.Map;

import java.awt.*;
import java.util.ArrayList;

public class GameWorld
{
    // Map Settings
    private Map map;

    // Macro Objects Settings
    private final ArrayList<NaziBase> naziBases = new ArrayList<>();

    public GameWorld() {
        initClasses();
    }

    public void initClasses() {
        // Macro Objects Initialization
        naziBases.add(createNaziBase(new Vector2<>(200.0, 100.0), Color.BLUE, "Werwolf Nazi Base"));
        naziBases.add(createNaziBase(new Vector2<>(200.0, 300.0), Color.RED, "Secret Nazi Base in Krasnohrad"));
        naziBases.add(createNaziBase(new Vector2<>(200.0, 500.0), Color.YELLOW, "Yalta \"DZIDZIO\" Nazi Base"));
    }

    private NaziBase createNaziBase(Vector2<Double> position, Color color, String name) {
        NaziBase base = new NaziBase(position);
        base.InitializeBaseSettings(color, name);
        return base;
    }

    public Map getMap() {
        return map;
    }

    public ArrayList<NaziBase> getNaziBases() { return naziBases; }

    public NaziBase getNaziBaseByName(String baseName) {
        for (NaziBase base : naziBases) {
            if (base.getName().equalsIgnoreCase(baseName)) {
                return base;
            }
        }
        return null;
    }

    public <T extends Infantry> ArrayList<T> getAllControllableEntitiesFromHierarchyByType(Class<T> entityType) {
        ArrayList<T> controllableEntities = new ArrayList<>();

        for (NaziBase base : naziBases) {
            for (Infantry entity : base.getEntities()) {
                if (entityType.isInstance(entity) && entity.getControllableComponent().isControllable()) {
                    controllableEntities.add((T) entity);
                }
            }
        }

        return controllableEntities;
    }

    public ArrayList<Infantry> getAllControllableEntitiesFromHierarchy() {
        ArrayList<Infantry> allControllableEntities = new ArrayList<>();

        for (NaziBase base : naziBases) {
            for (Infantry entity : base.getEntities()) {
                if (entity.getControllableComponent().isControllable()) {
                    allControllableEntities.add(entity);
                }
            }
        }

        return allControllableEntities;
    }

    public void Render(Graphics g) {
        //Map.Draw(g);
        for (var naziBase : naziBases) {
            naziBase.draw(g);
        }
    }

    public void Update() {
        //Map.Update();
        for (var naziBase : naziBases) {
            naziBase.update();
        }
    }

}
