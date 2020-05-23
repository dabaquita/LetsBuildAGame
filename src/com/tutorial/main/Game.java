package com.tutorial.main;

// Denielle Abaquita
// 2020-04-03

import com.tutorial.main.Enemies.BasicEnemy;
import com.tutorial.main.GameObject.ID;
import com.tutorial.main.UserInterface.HUD;
import com.tutorial.main.UserInterface.KeyInput;
import com.tutorial.main.UserInterface.Menu;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable
{
    private static final long serialVersionUID = -3905537379132692645L;

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private HUD hud;
    private Spawner spawner;

    private Menu menu;

    public enum STATE
    {
      Menu,
        Help,
        Game
    };

    public STATE gameState = STATE.Menu;

    // Initializing game elements
    public Game()
    {
        // Handler
        handler = new Handler();

        // Menu
        menu = new Menu(this, handler);

        // Listeners
        this.addKeyListener(new KeyInput(handler));
        this.addMouseListener(menu);

        // Window
        new Window(WIDTH, HEIGHT, "Let's Build a Game!", this);

        // HUD
        hud = new HUD();

        // Spawner
        spawner = new Spawner(handler, hud);

        // Let's start spawning the game objects
        if (gameState == STATE.Game)
        {
            // Player and Enemies
            handler.addObject(new Player(WIDTH / 2 - 32, HEIGHT / 2 - 32, ID.Player, handler));
            handler.addObject(new BasicEnemy(WIDTH / 2 + 32, HEIGHT / 2 + 32, ID.GeneralEnemy, handler));
        }
    }

    public synchronized void start()
    {
        thread = new Thread(this);
        running = true;
        thread.start();
    }

    public synchronized void stop()
    {
        try
        {
            thread.join();
            running = false;
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        thread = new Thread(this);
        thread.start();
    }

    public void run()
    {
        // Automatically focuses on window
        this.requestFocus();

        // Famous Game Loop - credited to someone unknown
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;

        while (running)
        {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;

            while (delta >= 1)
            {
                tick();
                delta--;
            }

            if (running)
                render();

            frames++;

            if (System.currentTimeMillis() - timer > 1000)
            {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }

        stop();
    }

    // Updates game objects
    private void tick()
    {
        handler.tick();

        // If we are in the Game state, then let's
        // start ticking the actual game
        if (gameState == STATE.Game)
        {
            hud.tick();
            spawner.tick();
        }
        else if (gameState == STATE.Menu)
        {
            menu.tick();
        }
    }

    // Renders the game graphics
    private void render()
    {
        BufferStrategy bs = this.getBufferStrategy();

        // Stick with no more than 3 buffers
        if (bs == null)
        {
            this.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);

        // If we are in the Game state, then let's
        // render the player HUD
        if (gameState == STATE.Game)
        {
            hud.render(g);
        }
        else if (gameState == STATE.Menu || gameState == STATE.Help)
        {
            menu.render(g);
        }

        g.dispose();
        bs.show();
    }

    // Makes sure an object stays within the bounds of the Game
    public static int clamp(int val, int min, int max)
    {
        if (val >= max)
            return val = max;
        else if (val <= min)
            return val = min;

        return val;
    }

    // Main Method
    public static void main(String[] args)
    {
        new Game();
    }

}
