package com.tutorial.main.GameObject;

// Denielle Abaquita
// 2020-04-08

// Attribute to enemies that allows for a
// transparent trail behind their movement

import com.tutorial.main.GameObject.GameObject;
import com.tutorial.main.GameObject.ID;
import com.tutorial.main.Handler;

import java.awt.*;

public class Trail extends GameObject
{
    private float alpha = 1;
    private Handler handler;
    private Color color;

    private int width, height;

    // Where life = 0.001 to 0.1
    private float life;

    public Trail(float x, float y, ID id, Color color, int width, int height, float life, Handler handler)
    {
        super(x, y, id);

        this.handler = handler;
        this.color = color;
        this.width = width;
        this.height = height;
        this.life = life;
    }

    @Override
    public void tick()
    {
        if (alpha > life)
            alpha -= (life - 0.0001f);
        else
            handler.removeObject(this);
    }

    @Override
    public void render(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;

        // Makes objects below it transparent
        g2d.setComposite(makeTransparent(alpha));

        g.setColor(color);
        g.fillRect((int) x, (int) y, width, height);

        // Makes other objects back to solid
        g2d.setComposite(makeTransparent(1));
    }

    @Override
    public Rectangle getBounds()
    {
        return null;
    }

    // Renders out the transparencies in the object
    private AlphaComposite makeTransparent(float alpha)
    {
        int type = AlphaComposite.SRC_OVER;
        return AlphaComposite.getInstance(type, alpha);
    }

    @Override
    public String toString()
    {
        return "Trail Object with x: " + x + " y: " + y + "\n";
    }
}
