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

import java.awt.image.DataBufferInt;

/**
 *
 * @author majoolwip
 */
public class Renderer
{
    private int[] p;
    private int w,h;
    
    public Renderer()
    {
        this.w = MajEngine.getWindow().getWidth();
        this.h = MajEngine.getWindow().getHeight();
        this.p = ((DataBufferInt)MajEngine.getWindow().getImage().getRaster().getDataBuffer()).getData();
    }
    
    public void clear()
    {
        for(int i = 0; i < p.length; i++)
        {
            p[i] = 0;
        }
    }
}
