package com.tutorial.main.UserInterface;

// Denielle Abaquita
// 2020-04-04

import com.tutorial.main.Game;
import com.tutorial.main.GameObject.GameObject;
import com.tutorial.main.Handler;
import com.tutorial.main.GameObject.ID;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter
{
    private Handler handler;

    public KeyInput(Handler handler)
    {
        this.handler = handler;
    }

    public void keyPressed(KeyEvent e)
    {
        int key = e.getKeyCode();

        for (GameObject object : handler.objects)
        {
            // All key events for player
            if (object.getId() == ID.Player)
            {
                // Basic WASD motion (using velocities)
                if (key == KeyEvent.VK_W)
                    object.setVelY(object.getVelY() - 5);
                else if (key == KeyEvent.VK_S)
                    object.setVelY(object.getVelY() + 5);
                else if (key == KeyEvent.VK_A)
                    object.setVelX(object.getVelX() - 5);
                else if (key == KeyEvent.VK_D)
                    object.setVelX(object.getVelX() + 5);

                // Set max velocities
                object.setVelY(Game.clamp((int) object.getVelY(), -5, 5));
                object.setVelX(Game.clamp((int) object.getVelX(), -5, 5));
            }
        }

        if (key == KeyEvent.VK_ESCAPE)
            System.exit(1);
    }

    public void keyReleased(KeyEvent e)
    {
        int key = e.getKeyCode();

        for (GameObject object : handler.objects)
        {
            // All key events for player
            if (object.getId() == ID.Player)
            {
                // Basic WASD motion (using velocities)
                if (key == KeyEvent.VK_W)
                    object.setVelY(object.getVelY() + 5);
                else if (key == KeyEvent.VK_S)
                    object.setVelY(object.getVelY() - 5);
                else if (key == KeyEvent.VK_A)
                    object.setVelX(object.getVelX() + 5);
                else if (key == KeyEvent.VK_D)
                    object.setVelX(object.getVelX() - 5);

                // Set max velocities
                object.setVelY(Game.clamp((int) object.getVelY(), -5, 5));
                object.setVelX(Game.clamp((int) object.getVelX(), -5, 5));
            }
        }
    }

}
