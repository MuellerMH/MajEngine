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

import com.majoolwip.engine.Renderer;
import com.majoolwip.engine.gfx.Font;

/**
 *
 * @author Majoolwip
 */
public class GUILabel extends GUIObject
{
    private Font font = Font.STANDARD;
    
    private String text;
    private int x, y;
    private int textColor;
    
    public GUILabel(String text, int x, int y)
    {
        this.text = text;
        this.x = x;
        this.y = y;
    }
    
    @Override
    public void update(float dt) {}

    @Override
    public void render(Renderer r) 
    {
        r.setDepth(depth);
        r.setFont(font);
        r.drawString(text, textColor, x, y);
    }
    
}
