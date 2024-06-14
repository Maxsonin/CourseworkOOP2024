package serialization;

import main.GameWorld;
import utils.Vector2;

import java.io.Serializable;

public class SerializationFile {
    private GameWorld gameWorld;

    public SerializationFile(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    // Nested class for serializing Entity information
    public static class EntityFields implements Serializable {
        public Vector2<Double> position;
        public String ID;
        public boolean needToGoToTargetBase;
        public boolean isControllable;
        public String classType;
        public int health;

        public EntityFields(Vector2<Double> position, String ID, boolean needToGoToTargetBase,
                            boolean isControllable, String classType, int health) {
            this.position = position.clone();
            this.ID = ID;
            this.needToGoToTargetBase = needToGoToTargetBase;
            this.isControllable = isControllable;
            this.classType = classType;
            this.health = health;
        }
    }

    // Nested class for serializing Base information
    public static class BaseFields implements Serializable {
        public Vector2<Double> position;
        public String name;

        public BaseFields(Vector2<Double> position, String name) {
            this.position = position;
            this.name = name;
        }
    }
}
