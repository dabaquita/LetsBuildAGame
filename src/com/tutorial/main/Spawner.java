package com.tutorial.main;

// Denielle Abaquita
// 4/27/20

import com.tutorial.main.Enemies.BasicEnemy;
import com.tutorial.main.Enemies.EnemyBoss;
import com.tutorial.main.Enemies.FastEnemy;
import com.tutorial.main.Enemies.SmartEnemy;
import com.tutorial.main.GameObject.ID;
import com.tutorial.main.UserInterface.HUD;

import java.util.Random;

public class Spawner
{
    private Handler handler;
    private HUD hud;
    private Random rand;

    private int scoreKeep = 0;

    public Spawner(Handler handler, HUD hud)
    {
        this.handler = handler;
        this.hud = hud;
        rand = new Random();
    }

    public void tick()
    {
        scoreKeep++;

        if (scoreKeep >= 500)
        {
            scoreKeep = 0;
            hud.setLevel(hud.getLevel() + 1);

            if (hud.getLevel() == 2)
            {
                handler.addObject(new BasicEnemy(rand.nextInt(Game.WIDTH - 16),
                        rand.nextInt(Game.HEIGHT - 32),
                        ID.GeneralEnemy, handler));
                handler.addObject(new BasicEnemy(rand.nextInt(Game.WIDTH - 16),
                        rand.nextInt(Game.HEIGHT - 32),
                        ID.GeneralEnemy, handler));
            }
            else if (hud.getLevel() == 3)
            {
                handler.addObject(new FastEnemy(rand.nextInt(Game.WIDTH - 16),
                        rand.nextInt(Game.HEIGHT - 32),
                        ID.GeneralEnemy, handler));
            }
            else if (hud.getLevel() == 4)
            {
                handler.addObject(new SmartEnemy(rand.nextInt(Game.WIDTH - 16),
                        rand.nextInt(Game.HEIGHT - 32),
                        ID.GeneralEnemy, handler));
             }
            else if (hud.getLevel() == 5)
            {
                handler.clearEnemies();

                handler.addObject(new EnemyBoss(rand.nextInt(Game.WIDTH - 96),
                        rand.nextInt(Game.HEIGHT - 96),
                        ID.EnemyBoss, handler));
            }
        }
    }
}
