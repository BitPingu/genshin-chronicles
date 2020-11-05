package culminating;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
/**
 *
 * @author joey_
 */
public class Input implements KeyListener
{
    private boolean[] pressed;
    
    public Input()
    {
        pressed = new boolean[255];
    }
    
    public boolean isPressed(int keyCode)
    {
        return pressed[keyCode];
    }
    
    @Override
    public void keyTyped(KeyEvent ke) {
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        pressed[ke.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent ke) {
        pressed[ke.getKeyCode()] = false;

    }
    
}
