package com.tutorial.main;

// Denielle Abaquita
// 2020-04-03

import com.tutorial.main.GameObject.GameObject;
import com.tutorial.main.GameObject.ID;
import com.tutorial.main.UserInterface.HUD;

import java.awt.*;

public class Player extends GameObject
{
    Handler handler;

    // Constructor
    public Player(int x, int y, ID id, Handler handler)
    {
        super(x, y, id);
        this.handler = handler;
    }

    // Hit Box
    public Rectangle getBounds()
    {
        return new Rectangle((int) x, (int) y, 32, 32);
    }

    // Movement
    @Override
    public void tick()
    {
        x += velX;
        y += velY;

        x = Game.clamp((int) x, 0, Game.WIDTH - 33);
        y = Game.clamp((int) y, 0, Game.HEIGHT - 55);

        collision();
    }

    // Creates the graphic
    @Override
    public void render(Graphics g)
    {
        // Has method we need for collision
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(Color.WHITE);
        g.fillRect((int) x, (int) y, 32, 32);
    }

    // Handles the collisions
    private void collision()
    {
        for (GameObject object : handler.objects)
        {
            ID id = object.getId();
            
            if (id == ID.BasicEnemy || id == ID.FastEnemy
                || id == ID.SmartEnemy || id == ID.EnemyBoss)
            {
                // Collision code for enemy
                if (getBounds().intersects(object.getBounds()))
                {
                    HUD.health -= 2;
                }
            }
        }
    }

    @Override
    public String toString()
    {
        return "Player Object with x: " + x + " y: " + y + "\n";
    }
}
