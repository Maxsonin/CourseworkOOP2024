package serialization;

import bases.Base;
import entities.base.Infantry;
import main.GameWorld;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Serializer {

    public static void SerializeWithFileChooser(GameWorld gameWorld) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Serialized GameWorld");
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            try (
                    FileOutputStream fileOutputStream = new FileOutputStream(fileToSave);
                    ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
            ) {
                ArrayList<Infantry> entities = gameWorld.getEntities();
                ArrayList<Base> bases = gameWorld.getBases();

                // Serialize entities as EntityFields
                for (var entity : entities) {
                    SerializationFile.EntityFields entityFields = new SerializationFile.EntityFields(
                            entity.getPosition(), entity.getID(), entity.isNeedToGoToTargetBase(),
                            entity.getControllableComponent().isControllable(), entity.getClass().toString(),
                            entity.getHealthStatsComponent().getHealth());
                    objectOutputStream.writeObject(entityFields);
                }

                // Serialize bases as BaseFields
                for (var base : bases) {
                    SerializationFile.BaseFields baseFields = new SerializationFile.BaseFields(
                            base.getPosition(), base.getName());
                    objectOutputStream.writeObject(baseFields);
                }
                objectOutputStream.flush(); // Ensure that all data is written out

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
