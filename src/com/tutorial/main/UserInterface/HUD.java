package com.tutorial.main.UserInterface;

// Denielle Abaquita
// 2020-04-04
// Player Heads Up Display

import com.tutorial.main.Game;

import java.awt.*;

public class HUD
{
    // Using static since we will only have one player
    // with ONE health
    // NOTE: Do not do this if there are multiple healths
    public static int health;
    private int score = 0;
    private int level = 1;

    public HUD ()
    {
        health = 100;
    }

    public void tick()
    {
        health = Game.clamp(health, 0, 100);

        score++;
    }

    public void render(Graphics g)
    {
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);

        // Formula below found on YouTube... it slowly goes from green to red
        // depending on how low health is
        g.setColor(Color.getHSBColor((1f * health) / 360, 1f, 1f));
        g.fillRect(15, 15, health * 2, 32);

        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);

        g.drawString("Score: " + score, 15, 64);
        g.drawString("Level: " + level, 15, 80);
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public int getLevel()
    {
        return level;
    }

    public void setLevel(int level)
    {
        this.level = level;
    }
}
