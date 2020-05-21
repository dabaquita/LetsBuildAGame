package com.tutorial.main.Enemies;

// Denielle Abaquita
// 5/18/20

import com.tutorial.main.*;
import com.tutorial.main.GameObject.GameObject;
import com.tutorial.main.GameObject.ID;
import com.tutorial.main.GameObject.Trail;

import java.awt.*;
import java.util.Random;

public class EnemyBoss extends GameObject
{
    private Handler handler;
    private int timerStop;
    private int timerLaunchAttack;

    public EnemyBoss (float x, float y, ID id, Handler handler)
    {
        super(x, y, id);

        this.handler = handler;
        timerStop = 80;
        timerLaunchAttack = 50;

        velX = 0;
        velY = 2;
    }

    // Hit Box
    public Rectangle getBounds()
    {
        return new Rectangle((int) x, (int) y, 96, 96);
    }

    @Override
    public void tick()
    {
        x += velX;
        y += velY;

        // Stop the enemy
        if (timerStop <= 0)
        {
            velY = 0;
            timerLaunchAttack--;
        }
        else
        {
            timerStop--;
        }

        // Launch the attack
        if (timerLaunchAttack <= 0)
        {
            if (velX == 0) velX = 2;

            if (new Random().nextInt(10) == 0)
                handler.addObject(new EnemyBossBullet((int) x + 48, (int) y + 48, ID.GeneralEnemy, handler));
        }

        // Bounds
        if (y <= 0 || y >= Game.HEIGHT - 96)
            velY *= -1;
        if (x <= 0 || x >= Game.WIDTH - 96)
            velX *= -1;

        //handler.addObject(new Trail(x, y, ID.Trail, Color.red, 96, 96, 0.1f, handler));
    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect((int) x, (int) y, 96, 96);
    }

    @Override
    public String toString()
    {
        return "Enemy Boss Object with velX: " + velX + " velY: " + velY + "\n";
    }
}

