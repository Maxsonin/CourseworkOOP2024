// Class for handling all Keyboard Inputs

package inputs;

import bases.NaziBase;
import entitys.BaseClasses.Infantry;
import javafx.scene.chart.NumberAxis;
import main.GameWorld;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class KeyboardInputs implements KeyListener {
    private GameWorld gameWorld;


    public KeyboardInputs(GameWorld gameWorld) { this.gameWorld = gameWorld; }

    @Override
    public void keyPressed(KeyEvent e) {
        ArrayList<Infantry> controllableEntitiesFromHierarchy = gameWorld.getAllControllableEntitiesFromHierarchy();

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
            case KeyEvent.VK_DELETE:
                var naziBases = gameWorld.getNaziBases();

                for (NaziBase naziBase : naziBases) {
                    var entities = naziBase.getEntities();
                        for (Infantry activatedEntity : controllableEntitiesFromHierarchy) {
                            if (entities.contains(activatedEntity))
                            {
                                entities.remove(activatedEntity);
                            }
                        }
                }
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
}