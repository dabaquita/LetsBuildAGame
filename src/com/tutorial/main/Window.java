package com.tutorial.main;

// Denielle Abaquita
// 2020-04-03

import javax.swing.*;
import java.awt.*;

public class Window extends Canvas
{
    private static final long serialVersionUID = 7526440484224045041L;

    public Window(int width, int height, String title, Game game)
    {
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);

        game.start();
    }

}
