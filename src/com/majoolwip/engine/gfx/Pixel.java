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
package com.majoolwip.engine.gfx;

/**
 *
 * @author majoolwip
 */
public class Pixel
{
    public float alpha = 1.0f;
    public float red = 0.0f;
    public float green = 0.0f;
    public float blue = 0.0f;
    
    public Pixel(){}
    
    public Pixel(float alpha, float red, float green, float blue)
    {
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }
    
    public Pixel(int color)
    {
        alpha = (0xff & (color >> 24)) / 255f;
        red   = (0xff & (color >> 16)) / 255f;
        green = (0xff & (color >> 8)) / 255f;
        blue  = (0xff & color) / 255f;
    }
    
    public void blend(Pixel p)
    {
        this.alpha = p.alpha;
        this.red += (p.red - red) * alpha;
        this.green += (p.green - green) * alpha;
        this.blue += (p.blue - blue) * alpha;
    }
    
    @Override
    public int hashCode()
    {
        StringBuilder builder = new StringBuilder();
        builder.append(red);
        builder.append(green);
        builder.append(blue);
        return builder.toString().hashCode();
    }
    
    @Override
    public boolean equals(Object other)
    {
        if(other instanceof Pixel)
        {
            Pixel p = (Pixel)other;
            return red == p.red && green == p.green && blue == p.blue;
        }
        else
        {
            return false;
        }
    }
}
