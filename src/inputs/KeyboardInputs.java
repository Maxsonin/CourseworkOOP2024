// Class for handling all Keyboard Inputs

package inputs;

import bases.Base;
import bases.NaziBase;
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
        for (Infantry activatedEntity : controllableEntitiesFromHierarchy) {
            activatedEntity.getControllableComponent().handleMovement(keyCode);
        }

        switch (keyCode) {
            // Test if keyboard is responding
            case KeyEvent.VK_T:
                System.out.println("Keyboard is responding!");
                break;

            case KeyEvent.VK_ESCAPE:
                for (Infantry activatedEntity : controllableEntitiesFromHierarchy) {
                    activatedEntity.getControllableComponent().setControllable(false);
                }
                break;

            case KeyEvent.VK_INSERT:
                CreateDialog createDialog = new CreateDialog(gameWorld);
                createDialog.showCreateDialog();
                break;

            case KeyEvent.VK_DELETE:
                var bases = gameWorld.getBases();

                for (Base base : bases) {
                    var entities = base.getEntities();
                        for (Infantry activatedEntity : controllableEntitiesFromHierarchy) {
                            if (entities.contains(activatedEntity))
                            {
                                entities.remove(activatedEntity);
                            }
                        }
                }
                break;

            case KeyEvent.VK_1:
                map.setScalePercentage(1, gameWorld);
                break;
            case KeyEvent.VK_2:
                map.setScalePercentage(0.75, gameWorld);
                break;
            case KeyEvent.VK_3:
                map.setScalePercentage(0.5, gameWorld);
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