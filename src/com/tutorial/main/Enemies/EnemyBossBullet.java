package com.tutorial.main.Enemies;

// Denielle Abaquita
// 5/20/20

import com.tutorial.main.*;
import com.tutorial.main.GameObject.GameObject;
import com.tutorial.main.GameObject.ID;
import com.tutorial.main.GameObject.Trail;

import java.awt.*;
import java.util.Random;

public class EnemyBossBullet extends GameObject
{
    private Handler handler;
    private GameObject player, enemyBoss;

    public EnemyBossBullet(float x, float y, ID id, Handler handler)
    {
        super(x, y, id);
        int velocity;
        Random rand = new Random();

        this.handler = handler;

        for (GameObject object : handler.objects)
        {
            if (object.getId() == ID.Player)
                player = object;
            else if (object.getId() == ID.EnemyBoss)
                enemyBoss = object;
        }

        // This makes it so that the bullets move towards
        // the player's last location at the time of spawning
        velocity = rand.nextInt(4);
        velY = (player.getY() > enemyBoss.getY()) ? velocity : velocity - 4;

        velocity = rand.nextInt(4);
        velX = (player.getX() > enemyBoss.getX()) ? velocity : velocity - 4;
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
            handler.removeObject(this);
        if (x <= 0 || x >= Game.WIDTH - 16)
            handler.removeObject(this);

        handler.addObject(new Trail(x, y, ID.Trail, Color.red, 16, 16, 0.1f, handler));
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect((int) x, (int) y, 16, 16);
    }

    @Override
    public String toString()
    {
        return "Basic Enemey Object with velX: " + velX + " velY: " + velY + "\n";
    }
}
