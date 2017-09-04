/*
 * Copyright (C) 2017 majoolwip
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package com.majoolwip.engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 *
 * @author majoolwip
 * @description Create and manager the game window.
 */
public class Window
{

    private BufferedImage image;
    private Canvas canvas;
    private JFrame frame;
    private Graphics graphics;
    private BufferStrategy buffer;

    private String title = "MajEngine by Majoolwip";

    private int width = 320;
    private int height = 240;
    private float scale = 2f;

    public Window()
    {
        //Display Image setup
        image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        //Canvas Setup
        canvas = new Canvas();
        Dimension s = new Dimension((int) (width * scale), (int) (height * scale));
        canvas.setPreferredSize(s);
        canvas.setMaximumSize(s);
        canvas.setMinimumSize(s);

        //Frame Setup
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(canvas, BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

        //Buffer Setup
        canvas.createBufferStrategy(2);
        buffer = canvas.getBufferStrategy();
        graphics = buffer.getDrawGraphics();
    }

    public void update()
    {
        graphics.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);
        buffer.show();
    }

    public void dispose()
    {
        image.flush();
        graphics.dispose();
        buffer.dispose();
        frame.dispose();
    }
    
    //Getters and Setters///////////////////////////////////////////

    public BufferedImage getImage()
    {
        return image;
    }

    public Canvas getCanvas()
    {
        return canvas;
    }

    public JFrame getFrame()
    {
        return frame;
    }

    public Graphics getGraphics()
    {
        return graphics;
    }

    public BufferStrategy getBuffer()
    {
        return buffer;
    }

    public String getTitle()
    {
        return title;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public float getScale()
    {
        return scale;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public void setScale(float scale)
    {
        this.scale = scale;
    }
    
    
}
