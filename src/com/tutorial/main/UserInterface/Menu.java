package com.tutorial.main.UserInterface;

// Denielle Abaquita
// 5/23/20

import com.tutorial.main.Enemies.BasicEnemy;
import com.tutorial.main.Game;
import com.tutorial.main.GameObject.ID;
import com.tutorial.main.Handler;
import com.tutorial.main.Player;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Menu extends MouseAdapter
{
    private Game game;
    private Handler handler;

    public Menu (Game game, Handler handler)
    {
        this.game = game;
        this.handler = handler;
    }

    public void mousePressed(MouseEvent e)
    {
        int mx = e.getX(), my = e.getY();

        // Menu page button presses
        if (game.gameState == Game.STATE.Menu)
        {
            // Play Button
            if (mouseOver(mx, my, 210, 150, 200, 64))
            {
                game.gameState = Game.STATE.Game;

                handler.addObject(new Player(Game.WIDTH / 2 - 32, Game.HEIGHT / 2 - 32, ID.Player, handler));
                handler.addObject(new BasicEnemy(Game.WIDTH / 2 + 32, Game.HEIGHT / 2 + 32, ID.GeneralEnemy, handler));
            }

            // Help Button
            if (mouseOver(mx, my,210, 250, 200, 64))
                game.gameState = Game.STATE.Help;

            // Quit Button
            if (mouseOver(mx, my, 210, 350, 200, 64))
                System.exit(1);
        }

        // Help page button presses
        else if (game.gameState == Game.STATE.Help)
        {
            if (mouseOver(mx, my, 210, 350, 200, 64))
            {
                game.gameState = Game.STATE.Menu;
                return;
            }
        }
    }

    public void mouseReleased(MouseEvent e)
    {

    }

    // Returns true if the mouse coordinate is within
    // the borders of the given x and y params
    private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
    {
        if (mx > x && mx < x + width)
            if (my > y && my < y + height)
                return true;

        return false;
    }

    // This is a static page for the most part for now
    // so we are not going to change values (calling tick method)
    public void tick()
    {

    }

    public void render(Graphics g)
    {
        // Bigger Font
        Font titleFont = new Font("arial", 1, 50);

        // Button Font
        Font buttonFont = new Font("arial", 1, 30);

        if (game.gameState == Game.STATE.Menu)
        {
            g.setFont(titleFont);
            g.setColor(Color.white);
            g.drawString("Menu", 240, 100);

            // Play Button
            g.setFont(buttonFont);
            g.setColor(Color.white);
            g.drawString("Play", 280, 190);

            g.setColor(Color.white);
            g.drawRect(210, 150, 200, 64);

            // Help Button
            g.setFont(buttonFont);
            g.setColor(Color.white);
            g.drawString("Help", 280, 290);

            g.setColor(Color.white);
            g.drawRect(210, 250, 200, 64);

            // Quit Button
            g.setFont(buttonFont);
            g.setColor(Color.white);
            g.drawString("Quit", 280, 390);

            g.setColor(Color.white);
            g.drawRect(210, 350, 200, 64);
        }
        else if (game.gameState == Game.STATE.Help)
        {
            // Help Label at Top
            g.setFont(titleFont);
            g.setColor(Color.white);
            g.drawString("Help", 250, 100);

            // Label
            g.setFont(new Font("arial", 1, 20));
            g.drawString("Use WASD keys to move player and dodge enemies", 80, 200);

            // Back Button for Help
            g.setFont(buttonFont);
            g.setColor(Color.white);
            g.drawString("Back", 270, 390);

            g.setColor(Color.white);
            g.drawRect(210, 350, 200, 64);
        }
    }
}
