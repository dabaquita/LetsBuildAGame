package com.tutorial.main.Enemies;

// Denielle Abaquita
// 2020-05-15

import com.tutorial.main.*;
import com.tutorial.main.GameObject.GameObject;
import com.tutorial.main.GameObject.ID;
import com.tutorial.main.GameObject.Trail;

import java.awt.*;

public class FastEnemy extends GameObject
{
    private Handler handler;

    public FastEnemy(float x, float y, ID id, Handler handler)
    {
        super(x, y, id);

        this.handler = handler;

        velY = 5;
        velX = 10;
    }

    // Hit Box
    public Rectangle getBounds()
    {
        return new Rectangle((int) x, (int) y, 16, 16);
    }

    @Override
    public void tick()
    {
        x += velX;
        y += velY;

        if (y <= 0 || y >= Game.HEIGHT - 32)
            velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 16)
            velX *= -1;

        handler.addObject(new Trail(x, y, ID.Trail, Color.CYAN, 16, 16, 0.1f, handler));
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.CYAN);
        g.fillRect((int) x, (int) y, 16, 16);
    }

    @Override
    public String toString()
    {
        return "Fast Enemy Object with velX: " + velX + " velY: " + velY + "\n";
    }
}
