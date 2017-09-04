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

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/**
 *
 * @author majoolwip
 * @description Allow for user input through mouse and keyboard.
 */
public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener
{
    private final int NUM_KEYS = 256;
    private final int NUM_BUTTONS = 5;
    
    private boolean[] keys = new boolean[NUM_KEYS];
    private boolean[] keysLast = new boolean[NUM_KEYS];
    private boolean[] buttons = new boolean[NUM_BUTTONS];
    private boolean[] buttonsLast = new boolean[NUM_BUTTONS];
    
    private int mouseX, mouseY;
    private int prevMouseX, prevMouseY;
    private int mouseDx, mouseDy;
    
    private int wheel;
    
    private char keyTyped;

    public Input()
    {
        MajEngine.getWindow().getCanvas().addKeyListener(this);
        MajEngine.getWindow().getCanvas().addMouseListener(this);
        MajEngine.getWindow().getCanvas().addMouseMotionListener(this);
        MajEngine.getWindow().getCanvas().addMouseWheelListener(this);
        
        mouseX = 0;
        mouseY = 0;
        prevMouseX = 0;
        prevMouseY = 0;
        mouseDx = 0;
        mouseDy = 0;
    }
    
    public void update()
    {
        System.arraycopy(keys, 0, keysLast, 0, NUM_KEYS);
        System.arraycopy(buttons, 0, buttonsLast, 0, NUM_BUTTONS);
        
        mouseDx = prevMouseX - mouseX;
        mouseDy = prevMouseY - mouseY;
        
        prevMouseX = mouseX;
        prevMouseY = mouseY;
        
        keyTyped = 0;
        wheel = 0;   
    }
    
    public boolean isKey(int keyCode)
    {
        return keys[keyCode];
    }
    
    public boolean isKeyDown(int keyCode)
    {
        return keys[keyCode] && !keysLast[keyCode];
    }
    
    public boolean isKeyUp(int keyCode)
    {
        return !keys[keyCode] && keysLast[keyCode];
    }
    
    public boolean isButton(int button)
    {
        return buttons[button];
    }
    
    public boolean isButtonDown(int button)
    {
        return buttons[button] && !buttonsLast[button];
    }
    
    public boolean isButtonUp(int button)
    {
        return !buttons[button] && buttonsLast[button];
    }
    
    public int getMouseX()
    {
        return mouseX;
    }
    
    public int getMouseY()
    {
        return mouseY;
    }
    
    public int getMouseDx()
    {
        return mouseDx;
    }
    
    public int getMouseDy()
    {
        return mouseDy;
    }
    
    public char getKeyTyped()
    {
        return keyTyped;
    }
    
    public int getWheel()
    {
        return wheel;
    }

    @Override
    public void keyTyped(KeyEvent ke)
    {
        keyTyped = ke.getKeyChar();
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        if(ke.getKeyCode() >= NUM_KEYS)
            return;
        keys[ke.getKeyCode()] = true;
    } 

    @Override
    public void keyReleased(KeyEvent ke)
    {
        if(ke.getKeyCode() >= NUM_KEYS)
            return;
        keys[ke.getKeyCode()] = false;
    }

    @Override
    public void mouseClicked(MouseEvent me)
    {
    }

    @Override
    public void mousePressed(MouseEvent me)
    {
        if(me.getButton() >= NUM_BUTTONS)
            return;
        buttons[me.getButton()] = true;
    }

    @Override
    public void mouseReleased(MouseEvent me)
    {
        if(me.getButton() >= NUM_BUTTONS)
            return;
        buttons[me.getButton()] = false;
    }

    @Override
    public void mouseEntered(MouseEvent me)
    {
    }

    @Override
    public void mouseExited(MouseEvent me)
    {
    }

    @Override
    public void mouseDragged(MouseEvent me)
    {
        mouseX = (int)(me.getX() / MajEngine.getWindow().getScale());
        mouseY = (int)(me.getY() / MajEngine.getWindow().getScale());
    }

    @Override
    public void mouseMoved(MouseEvent me)
    {
        mouseX = (int)(me.getX() / MajEngine.getWindow().getScale());
        mouseY = (int)(me.getY() / MajEngine.getWindow().getScale());
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent mwe)
    {
        wheel = mwe.getWheelRotation();
    }
    
}
