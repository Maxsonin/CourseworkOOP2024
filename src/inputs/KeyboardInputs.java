package inputs;

import map.Map;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyboardInputs implements KeyListener {

    private Map map;

    public KeyboardInputs(Map map) {
        this.map = map;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int amount = map.GetAmountOfDisplacement();

        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_S:
                map.moveViewDown(amount);
                System.out.println("S");
                break;
            case KeyEvent.VK_W:
                map.moveViewUp(amount);
                System.out.println("W");
                break;
            case KeyEvent.VK_D:
                map.moveViewRight(amount);
                System.out.println("D");
                break;
            case KeyEvent.VK_A:
                map.moveViewLeft(amount);
                System.out.println("A");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}