/*
 * Copyright (C) 2017 Majoolwip
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
package com.majoolwip.engine.gui;

import com.majoolwip.engine.Input;
import com.majoolwip.engine.MajEngine;
import com.majoolwip.engine.Renderer;
import com.majoolwip.engine.gfx.Font;
import java.awt.event.MouseEvent;

/**
 *
 * @author Majoolwip
 */
public class GUIButton extends GUIObject
{
    private String text;
    private Font font = Font.STANDARD;
    
    private int borderColor, backgroundColor, textColor, hoverColor;
    private int x,y,w,h;
    private int xBorder, yBorder;
    
    private boolean hover;
    
    public GUIButton()
    {
        this("Button", 0, 0, 2, 2);
    }
    
    public GUIButton(String text, int x, int y, int xBorder, int yBorder)
    {
        this.x = x;
        this.y = y;
        this.text = text;
        this.xBorder = xBorder;
        this.yBorder = yBorder;
        
        w = font.getStringWidth(text) + xBorder * 2;
        h = font.fontImage.height + yBorder * 2;
         
        borderColor = 0xffffffff;
        backgroundColor = 0xff000000;
        textColor = 0xffffffff;
        hoverColor = 0xff101010;
        
        hover = false;
    }
    
    @Override
    public void update(float dt) 
    {
        hover = false;
        Input input = MajEngine.getInput();
        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();
        if(mouseX > x && mouseX < x + w)
        {
            if(mouseY > y && mouseY < y + h)
            {
                hover = true;
                
                if(input.isButtonUp(MouseEvent.BUTTON1))
                {
                    if(event != null)
                        event.event(new String[]{"LEFT_CLICKED"});
                }
            }
        }
    }

    @Override
    public void render(Renderer r) 
    {
        r.setDepth(depth);
        r.setFont(font);
        r.drawBoxFill(x, y, w, h, hover ? hoverColor : backgroundColor);
        r.drawBox(x, y, w, h, borderColor);
        r.drawString(text, textColor, x + xBorder, y + yBorder);
    }
    
}
