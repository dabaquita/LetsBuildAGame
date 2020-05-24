package com.tutorial.main.GameObject;

// Denielle Abaquita
// 5/24/20

import com.tutorial.main.*;
import com.tutorial.main.GameObject.GameObject;
import com.tutorial.main.GameObject.ID;
import com.tutorial.main.GameObject.Trail;

import java.awt.*;
import java.util.Random;

public class MenuParticle extends GameObject
{
    private Handler handler;
    private Random rand = new Random();
    private Color col;
    private int dir = 0;

    public MenuParticle(float x, float y, ID id, Handler handler)
    {
        super(x, y, id);

        this.handler = handler;
        dir = rand.nextInt(2);

        velX = rand.nextInt(10) - 5;
        velY = rand.nextInt(10) - 5;

        col = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
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

        handler.addObject(new Trail(x, y, ID.Trail, col, 16, 16, 0.1f, handler));
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(col);
        g.fillRect((int) x, (int) y, 16, 16);
    }

    @Override
    public String toString()
    {
        return "Fast Enemy Object with velX: " + velX + " velY: " + velY + "\n";
    }
}

