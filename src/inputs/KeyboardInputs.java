// Class for handling all Keyboard Inputs

package inputs;

import dialogBoxes.CreateDialog;
import entitys.base.Infantry;
import main.GameWorld;
import map.Map;

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
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
}