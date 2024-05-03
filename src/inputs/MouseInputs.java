// Class for handling all Mouse Inputs

package inputs;

import Bases.NaziBase;
import Entitys.Entity;
import main.GameWorld;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private GameWorld gameWorld;

    public MouseInputs(GameWorld gameWorld) {
        this.gameWorld = gameWorld;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        ArrayList<NaziBase> naziBases = gameWorld.getNaziBases();

        for (var naziBase : naziBases) {
            for (var entity : naziBase.getEntities()) {
                if (isClickWithinBounds(entity, e.getX(), e.getY())) {
                    entity.getControllableComponent().setControllable(!entity.getControllableComponent().isControllable());
                    break;
                }
            }
        }

        System.out.println("Click");
    }

    // Helper method to check if a click is within the bounds of an entity
    private boolean isClickWithinBounds(Entity entity, int mouseX, int mouseY) {
        // Get entity's position and dimensions
        double attackerX = entity.getPosition().getX();
        double attackerY = entity.getPosition().getY();
        int attackerWidth = (int)entity.getImgSize().getWidth() * (int)entity.getScaleFactor();
        int attackerHeight = (int)entity.getImgSize().getHeight() * (int)entity.getScaleFactor();

        // Check if the mouse click coordinates are within the entity's bounds
        if (mouseX >= attackerX && mouseX <= attackerX + attackerWidth &&
                mouseY >= attackerY && mouseY <= attackerY + attackerHeight) {
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) { }

    @Override
    public void mouseReleased(MouseEvent e) { }

    @Override
    public void mouseEntered(MouseEvent e) { }

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseDragged(MouseEvent e) { }

    @Override
    public void mouseMoved(MouseEvent e) { }
}
