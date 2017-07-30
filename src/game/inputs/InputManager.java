package game.inputs;

import java.awt.event.KeyEvent;

/**
 * Created by VALV on 7/18/2017.
 */
public class InputManager {
    public boolean leftPress;
    public boolean rightPress;
    public boolean downPress;
    public boolean upress;
    public boolean xPress;

    public void keyPress(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                rightPress = true;
                break;
            case KeyEvent.VK_LEFT:
                leftPress = true;
                break;
            case KeyEvent.VK_UP:
                upress = true;
                break;
            case KeyEvent.VK_DOWN:
                downPress = true;
                break;
            case KeyEvent.VK_X:
                xPress = true;
                break;
            default:
                break;
        }

    }

    public void keyRelease(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                rightPress = false;
                break;
            case KeyEvent.VK_LEFT:
                leftPress = false;
                break;
            case KeyEvent.VK_UP:
                upress = false;
                break;
            case KeyEvent.VK_DOWN:
                downPress = false;
                break;
            case KeyEvent.VK_X:
                xPress = false;
                break;
            default:
                break;
        }
    }
}
