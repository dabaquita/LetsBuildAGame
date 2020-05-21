package com.tutorial.main;

// Denielle Abaquita
// 2020-04-03

// Updates and renders all objects in the room

import com.tutorial.main.GameObject.GameObject;
import com.tutorial.main.GameObject.ID;

import java.awt.*;
import java.util.LinkedList;

public class Handler
{
    public LinkedList<GameObject> objects = new LinkedList<>();

    public void tick()
    {
        // Note: modified for loop does not work here
        // because of of Trail.java for some reason
        for (int i = 0; i < objects.size(); i++)
            objects.get(i).tick();
    }

    public void render(Graphics g)
    {
        for (GameObject gameObject : objects)
            gameObject.render(g);
    }

    public void addObject(GameObject gameObject)
    {
        this.objects.add(gameObject);
    }

    public void removeObject(GameObject gameObject)
    {
        this.objects.remove(gameObject);
    }

    public void clearEnemies()
    {
        objects.removeIf(object -> object.getId() == ID.GeneralEnemy);
    }
}
