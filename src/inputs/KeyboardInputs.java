// Class for handling all Keyboard Inputs

package inputs;

import dialogBoxes.CreateDialog;
import entities.base.Infantry;
import main.GameWorld;
import map.Map;
import serialization.Deserializer;
import serialization.Serializer;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyboardInputs implements KeyListener {
    private GameWorld gameWorld;


    public KeyboardInputs(GameWorld gameWorld) { this.gameWorld = gameWorld; }

    @Override
    public void keyPressed(KeyEvent e) {
        Map map = gameWorld.getMap();

        ArrayList<Infantry> controllableEntitiesFromHierarchy = gameWorld.getAllControllableEntities();

        int keyCode = e.getKeyCode();
        for (Infantry activatedEntity : controllableEntitiesFromHierarchy) { activatedEntity.getControllableComponent().handleMovement(keyCode); } // Requirement №29
        switch (keyCode) {
            case KeyEvent.VK_T:
                System.out.println("Keyboard is responding!");
                break;

            case KeyEvent.VK_ESCAPE: // Requirement №31
                for (Infantry activatedEntity : controllableEntitiesFromHierarchy) {
                    activatedEntity.getControllableComponent().setControllable(false);
                }
                break;

            case KeyEvent.VK_INSERT:
                CreateDialog createDialog = new CreateDialog(gameWorld);
                createDialog.showCreateDialog();
                break;

            case KeyEvent.VK_DELETE: // Requirement №30
                for (var entity : controllableEntitiesFromHierarchy) {
                    gameWorld.getEntities().remove(entity);
                }
                break;

            case KeyEvent.VK_H:
                gameWorld.moveEntitiesToMainBase();
                break;

            case KeyEvent.VK_W:
                map.moveViewUp(gameWorld);
                break;
            case KeyEvent.VK_A:
                map.moveViewLeft(gameWorld);
                break;
            case KeyEvent.VK_S:
                map.moveViewDown(gameWorld);
                break;
            case KeyEvent.VK_D:
                map.moveViewRight(gameWorld);
                break;
        }

        // Check for Ctrl+S to Serialize
        if ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0 && keyCode == KeyEvent.VK_S) {
            Serializer.SerializeWithFileChooser(gameWorld);
        }
        // Check for Ctrl+S to Serialize
        else if ((e.getModifiersEx() & KeyEvent.CTRL_DOWN_MASK) != 0 && keyCode == KeyEvent.VK_L) {
            Deserializer.DeserializeWithFileChooser(gameWorld);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
}