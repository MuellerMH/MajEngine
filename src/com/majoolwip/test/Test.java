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
package com.majoolwip.test;

import com.majoolwip.engine.MajEngine;
import com.majoolwip.engine.Renderer;
import com.majoolwip.engine.State;
import com.majoolwip.engine.gui.GUIButton;
import com.majoolwip.engine.gui.GUIContainer;

/**
 *
 * @author majoolwip
 */
public class Test extends State
{
    public GUIContainer gc;

    @Override
    public void init() 
    {
        gc = new GUIContainer();
        gc.setShow(true);
        gc.add(new GUIButton("Test Button", 25,25,2,2));
    }

    @Override
    public void update(float dt) 
    {
        gc.update(dt);
    }

    @Override
    public void render(Renderer r) 
    {
        gc.render(r);
    }
    
    public static void main(String args[])
    {
        MajEngine eng = new MajEngine(new Test());
        eng.start();
    }
}
