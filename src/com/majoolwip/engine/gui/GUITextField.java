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
public class GUITextField extends GUIObject
{
    private Font font = Font.STANDARD;
    
    private int x,y,w,h;
    private String text;
    private int borderColor, backgroundColor, textColor, cursorColor;
    private int cursorLocation;
    private boolean focus;
    
    public GUITextField(int x, int y, int numChars)
    {
        this.x = x;
        this.y = y;
        this.w = font.getAvgCharWidth() * numChars;
        this.h = font.fontImage.height + 2;
        
        borderColor = 0xffffffff;
        backgroundColor = 0xff000000;
        textColor = 0xffffffff;
        cursorColor = 0xffffffff;
        cursorLocation = 0;
        focus = false;
    }
    
    @Override
    public void update(float dt) 
    {
        focus = false;
        Input input = MajEngine.getInput();
        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();
        if(mouseX > x && mouseX < x + w)
        {
            if(mouseY > y && mouseY > y + h)
            {
                if(input.isButtonUp(MouseEvent.BUTTON1))
                {
                    focus = true;
                }
            }
        }
        
        if(focus)
        {
            char typed = input.getKeyTyped();
            if(typed < 32)
                return;
        }
    }

    @Override
    public void render(Renderer r) 
    {
        
    }
    
}
