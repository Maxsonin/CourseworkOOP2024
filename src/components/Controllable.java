// Component to give Entity an ability to be controlled by User

package components;

import entitys.Entity;
import utils.Vector2;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Controllable {
    private Entity parentObj;
    private boolean isControllable = false;

    private final double velocityInControllable = 5.0;

    public Controllable(Entity parentObj) {
        this.parentObj = parentObj;
    }

    public boolean isControllable() { return isControllable; }
    public void setControllable(boolean newIsControllable) { isControllable = newIsControllable; }

    public void drawBorder(Graphics g) {
        if (parentObj == null) { return; }

        Vector2<Double> objPosition = parentObj.getPosition();
        Dimension objSize = parentObj.getImgSize();

        if (objPosition == null || objSize == null) { return; }

        int x = (int) Math.round(objPosition.getX());
        int y = (int) Math.round(objPosition.getY());
        int width = (int) Math.round(objSize.getWidth());
        int height = (int) Math.round(objSize.getHeight());

        g.setColor(Color.RED);
        g.drawRect(x, y, (int)(width * parentObj.getScaleFactor()), (int)(height * parentObj.getScaleFactor()));
    }

    public void handleMovement(int keyCode) {
        if (!isControllable || parentObj == null) { return; }

        Vector2<Double> objPosition = parentObj.getPosition();

        switch (keyCode) {
            case KeyEvent.VK_UP:
                objPosition.moveY(-velocityInControllable);
                break;
            case KeyEvent.VK_DOWN:
                objPosition.moveY(velocityInControllable);
                break;
            case KeyEvent.VK_LEFT:
                objPosition.moveX(-velocityInControllable);
                break;
            case KeyEvent.VK_RIGHT:
                objPosition.moveX(velocityInControllable);
                break;
        }
    }
}
