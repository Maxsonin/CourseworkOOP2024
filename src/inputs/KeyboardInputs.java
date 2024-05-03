// Class for handling all Keyboard Inputs

package inputs;

import Entitys.BaseClasses.Infantry;
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
        for (Infantry activatedAttacker : controllableEntitiesFromHierarchy) {
            activatedAttacker.getControllableComponent().handleMovement(keyCode);
        }

        switch (keyCode) {
            // Test if keyboard is responding
            case KeyEvent.VK_T:
                System.out.println("Keyboard is responding!");
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyReleased(KeyEvent e) { }
}