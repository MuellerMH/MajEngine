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
public class GUIContainer 
{
    private class Node
    {
        public GUIObject object;
        public Node next;
        
        public Node(GUIObject object, Node next)
        {
            this.object = object;
            this.next = next;
        }
    }
    
    private Node head;
  
    private boolean focus = false;
    private boolean show = true;
    
    public GUIContainer()
    {
        head = null;
    }
    
    public void update(float dt)
    {
        Node prev = null;
        Node cur = head;
        while(cur != null)
        {
            cur.object.update(dt);
            if(cur.object.isDead())
            {
                if(prev != null)
                    prev.next = cur.next;
                else
                    head = head.next;    
            }
            prev = cur;
            cur = cur.next;
        }
    }
    
    public void render(Renderer r)
    {
        Node cur = head;
        while(cur != null)
        {
            cur.object.render(r);
            cur = cur.next;
        }
    }

    public void add(GUIObject object)
    {
        Node newNode = new Node(object, head);
        head = newNode;
    }
    
    public boolean isFocus() 
    {
        return focus;
    }

    public void setFocus(boolean focus) 
    {
        this.focus = focus;
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }
    
    
}
