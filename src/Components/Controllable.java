// Component to give Entity an ability to be controlled by User

package Components;

import Entitys.Entity;
import Utils.Vector2;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Controllable {
    private Entity parentObj;
    private boolean isControllable = false;

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
        g.drawRect(x, y, width, height);
    }

    public void handleMovement(int keyCode) {
        if (!isControllable || parentObj == null) { return; }

        Vector2<Double> objPosition = parentObj.getPosition();
        double objVelocity = parentObj.getVelocity();

        switch (keyCode) {
            case KeyEvent.VK_UP:
                objPosition.moveY(-objVelocity);
                System.out.println("Up");
                break;
            case KeyEvent.VK_DOWN:
                objPosition.moveY(objVelocity);
                System.out.println("Down");
                break;
            case KeyEvent.VK_LEFT:
                objPosition.moveX(-objVelocity);
                System.out.println("Left");
                break;
            case KeyEvent.VK_RIGHT:
                objPosition.moveX(objVelocity);
                System.out.println("Right");
                break;
        }
    }
}
