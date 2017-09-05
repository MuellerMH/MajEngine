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

import com.majoolwip.engine.gfx.Font;
import com.majoolwip.engine.gfx.Image;
import java.awt.image.DataBufferInt;

/**
 *
 * @author majoolwip
 */
public class Renderer
{
    private Font font = Font.STANDARD;
    
    private int[] pixels;
    private int[] depth;
    private int width, height;
    
    public Renderer()
    {
        width = MajEngine.getWindow().getWidth();
        height = MajEngine.getWindow().getHeight();
        pixels = ((DataBufferInt)MajEngine.getWindow().getImage().getRaster().getDataBuffer()).getData();
        depth = new int[pixels.length];
    }
    
    public void clearDepth()
    {
        for(int i = 0; i < depth.length; i++)
        {
            depth[i] = 0;
        }
    }
    
    public void setPixel(int x, int y, int value)
    {
        if(x < 0 || x >= width || y < 0 || y >= height)
            return;
        int index = x + y * width;
        pixels[index] = value;
    }
    
    public void drawString(String text, int color, int offX, int offY)
    {
        for(int i = 0; i < text.length(); i++)
        {
            int unicode = text.codePointAt(i);
            if(unicode >= font.getNumberOfUnicodes())
                unicode = 0;
            for(int x = 0; x < font.widths[unicode]; x++)
            {
                for(int y = 1; y < font.fontImage.height; y++)
                {
                    setPixel(x + offX, y + offY, font.fontImage.pixels[(x + font.offsets[unicode]) + y * font.fontImage.width]);
                }
            }
            offX += font.widths[unicode];
        }
    }
    
    public void drawImage(Image image, int offX, int offY)
    {
        for(int x = 0; x < image.width; x++)
        {
            for(int y = 0; y < image.height; y++)
            {
                setPixel(x + offX, y + offY, image.pixels[x + y * image.width]);
            }
        }
    }
    
    public void drawBox(int offX, int offY, int w, int h, int color)
    {
        for(int x = 0; x < w; x++)
        {
            setPixel(x + offX, offY, color);
            setPixel(x + offX, offY + (h - 1), color);
        }
        
        for(int y = 0; y < h; y++)
        {
            setPixel(offX, y + offY, color);
            setPixel(offX + (w - 1), y + offY, color);
        }
    }
    
    public void drawBoxFill(int offX, int offY, int w, int h, int color)
    {
        for(int y = 0; y < h; y++)
        {
            for(int x = 0; x < w; x++)
            {
                setPixel(x + offX, y + offY, color);
            }
        }
    }
   
}
