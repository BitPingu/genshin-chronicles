package Entities;

import culminating.Controller;

import java.awt.event.KeyEvent;

public class PlayerController implements Controller
{
    private Input input;
    
    public PlayerController(Input input)
    {
        this.input = input;
    }

    @Override
    //returns true if w key is pressed
    public boolean isRequestingUp()
    {
        return input.isPressed(KeyEvent.VK_W);
    }
    
    //returns true if S key is pressed
    @Override
    public boolean isRequestingDown()
    {
        return input.isPressed(KeyEvent.VK_S);
    }

    //returns true if A key is pressed
    @Override
    public boolean isRequestingLeft()
    {
        return input.isPressed(KeyEvent.VK_A);
    }

    //returns true if D key is pressed
    @Override
    public boolean isRequestingRight()
    {
        return input.isPressed(KeyEvent.VK_D);
    }
    

}
