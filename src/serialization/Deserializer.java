package serialization;

import entities.base.Infantry;
import entities.nazi.NaziInfantry;
import entities.nazi.NaziKombat;
import entities.nazi.NaziSquadLeader;
import entities.soviets.SovietInfantry;
import entities.soviets.SovietKombat;
import entities.soviets.SovietSquadLeader;
import main.GameWorld;
import utils.SD;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Deserializer {

    public static void DeserializeWithFileChooser(GameWorld gameWorld) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Load Serialized GameWorld");
        int userSelection = fileChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();
            try (
                    FileInputStream fileInputStream = new FileInputStream(fileToLoad);
                    ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
            ) {
                ArrayList<Infantry> entities = new ArrayList<>();

                while (true) {
                    try {
                        Object obj = objectInputStream.readObject();
                        if (obj instanceof SerializationFile.EntityFields) {
                            SerializationFile.EntityFields entityFields = (SerializationFile.EntityFields) obj;
                            Infantry deserializedEntity = null;
                            if (entityFields.classType.contains(SD.Nazi)) {
                                if (entityFields.classType.contains(SD.Infantry))
                                {
                                    deserializedEntity = new NaziInfantry(entityFields);
                                }
                                else if (entityFields.classType.contains(SD.SquadLeader))
                                {
                                    deserializedEntity = new NaziSquadLeader(entityFields);
                                }
                                else if (entityFields.classType.contains(SD.Kombat))
                                {
                                    deserializedEntity = new NaziKombat(entityFields);
                                }
                            }
                            else if (entityFields.classType.contains(SD.Soviet)) {
                                if (entityFields.classType.contains(SD.Infantry))
                                {
                                    deserializedEntity = new SovietInfantry(entityFields);
                                }
                                else if (entityFields.classType.contains(SD.SquadLeader))
                                {
                                    deserializedEntity = new SovietSquadLeader(entityFields);
                                }
                                else if (entityFields.classType.contains(SD.Kombat))
                                {
                                    deserializedEntity = new SovietKombat(entityFields);
                                }
                            }

                            entities.add(deserializedEntity);
                        }
                    } catch (EOFException e) {
                        break; // End of file reached
                    }
                }

                gameWorld.setEntities(entities);

            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException("Error deserializing game world", e);
            }
        }
    }
}
