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
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Scanner;

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
    private int charLimit;
    private int xBorder, yBorder;
    private boolean focus;
    private boolean limitInput;
    private float blinkTimer;
    private boolean blink;
    
    public GUITextField(int x, int y, int numChars)
    {
        this.x = x;
        this.y = y;
        xBorder = 2;
        yBorder = 2;
        this.w = font.getAvgCharWidth() * numChars + xBorder * 2;
        this.h = font.fontImage.height + 2 + yBorder * 2;
        this.charLimit = numChars;
        
        borderColor = 0xffffffff;
        backgroundColor = 0xff000000;
        textColor = 0xffffffff;
        cursorColor = 0xffffffff;
        cursorLocation = 0;
        focus = false;
        text = "";
        limitInput = false;
        blinkTimer = 0;
        blink = true;
    }
    
    @Override
    public void update(float dt) 
    {
        blinkTimer += dt;
        if(blinkTimer >= 0.5f)
        {
            blink = !blink;
            blinkTimer = 0;
        }
        
        Input input = MajEngine.getInput();
        int mouseX = input.getMouseX();
        int mouseY = input.getMouseY();
        if(mouseX > x && mouseX < x + w)
        {
            if(mouseY > y && mouseY < y + h)
            {
                if(input.isButtonUp(MouseEvent.BUTTON1))
                {
                    focus = true;
                }
            }
            else
            {
                if(input.isButtonUp(MouseEvent.BUTTON1))
                {
                    focus = false;
                }
            }
        }
        else
        {
            if(input.isButtonUp(MouseEvent.BUTTON1))
            {
                focus = false;
            }
        }
        
        if(focus)
        {
            if(input.isKeyDown(KeyEvent.VK_LEFT))
            {
                if(cursorLocation > 1)
                    cursorLocation--;
                blink = true;
            }
            
            if(input.isKeyDown(KeyEvent.VK_RIGHT))
            {
                if(cursorLocation < text.length())
                    cursorLocation++;
                blink = true;
            }
            
            if(input.isKeyUp(KeyEvent.VK_ENTER))
            {
                if(event != null)
                {
                    event.event(new String[]{"ENTER_PRESSED"});
                }
            }
            
            char typed = input.getKeyTyped();
            if(typed == 8 && text.length() > 0 && cursorLocation != 0)
            {
                String newText = "";
                newText += text.substring(0, cursorLocation - 1);
                if(cursorLocation < text.length())
                    newText += text.substring(cursorLocation, text.length());
                text = newText;
                cursorLocation--;
                blink = true;
            }
            else if (typed == 127 && text.length() > 0 && cursorLocation != text.length())
            {
                String newText = "";
                newText += text.substring(0, cursorLocation);
                if(cursorLocation < text.length())
                    newText += text.substring(cursorLocation + 1, text.length());
                text = newText;
                blink = true;
            }           
            else if(typed >= 32 && typed < 127)
            {
                if(limitInput && text.length() == charLimit)
                    return;
                String newText = "";
                newText += text.substring(0, cursorLocation);
                newText += input.getKeyTyped();
                if(cursorLocation < text.length())
                    newText += text.substring(cursorLocation, text.length());
                cursorLocation++;
                text = newText;
                blink = true;
            }
        }
    }

    @Override
    public void render(Renderer r) 
    {
        r.setDepth(depth);
        r.setFont(font);
        r.setLimits(x, y, w, h);
        r.drawBoxFill(x, y, w, h, backgroundColor);
        r.drawBox(x, y, w, h, borderColor);
        int cursorOffset = font.getStringWidth(text.substring(0, cursorLocation));
        int textOffset = cursorOffset > w - xBorder * 2 ? (w - xBorder * 2) - cursorOffset : 0;
        r.drawString(text, textColor, x + xBorder + textOffset, y + yBorder);
        if(focus && blink)
        {
            r.drawBox(x + xBorder + cursorOffset + textOffset, y + yBorder, 1, h - yBorder * 2, cursorColor);
        }
        r.resetLimit();
    }
    
    
}
