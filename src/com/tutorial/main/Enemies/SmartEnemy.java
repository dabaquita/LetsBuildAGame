package com.tutorial.main.Enemies;

// Denielle Abaquita
// 5/15/20

import com.tutorial.main.*;
import com.tutorial.main.GameObject.GameObject;
import com.tutorial.main.GameObject.ID;
import com.tutorial.main.GameObject.Trail;

import java.awt.*;

public class SmartEnemy extends GameObject
{
    private Handler handler;
    private GameObject player;

    public SmartEnemy(int x, int y, ID id, Handler handler)
    {
        super(x, y, id);
        this.handler = handler;

        for (GameObject object : handler.objects)
            if (object.getId() == ID.Player)
                player = object;
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

        float diffX = x - player.getX() - 8;
        float diffY = y - player.getY() - 8;
        float distance = (float) Math.sqrt(Math.pow(x - player.getX(), 2) + Math.pow(y - player.getY(), 2));

        velX = 2 * ((-1 / distance) * diffX);
        velY = 2* ((-1 / distance) * diffY);

        if (y <= 0 || y >= Game.HEIGHT - 32)
            velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 16)
            velX *= -1;

        handler.addObject(new Trail(x, y, ID.Trail, Color.GREEN, 16, 16, 0.1f, handler));
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.GREEN);
        g.fillRect((int) x, (int) y, 16, 16);
    }

    @Override
    public String toString()
    {
        return "Smart Enemy Object with velX: " + velX + " velY: " + velY + "\n";
    }
}

