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

/**
 *
 * @author Majoolwip
 */
public abstract class GUIObject 
{
    protected GUIEvent event;
    protected GUIContainer parent;
    
    protected String tag = "null";
    protected boolean dead = false;
    protected int depth = 0;
    
    public abstract void update(float dt);
    public abstract void render(Renderer r);

    public void setEvent(GUIEvent event) {
        this.event = event;
    }

    public void setParent(GUIContainer parent) {
        this.parent = parent;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public GUIEvent getEvent() {
        return event;
    }

    public GUIContainer getParent() {
        return parent;
    }

    public String getTag() {
        return tag;
    }

    public boolean isDead() {
        return dead;
    }
}
