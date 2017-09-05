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
package com.majoolwip.engine.gfx;

/**
 *
 * @author Majoolwip
 */
public class Font 
{
    public static final Font STANDARD = new Font("/res/standard.png");
    
    public Image fontImage;
    public int[] offsets;
    public int[] widths;
    
    private int numberOfUnicodes = 0;
    private int avgCharWidth = 0;
    
    public Font(String path)
    {
        fontImage = new Image(path);
        
        for(int i = 0; i < fontImage.width; i++)
        {
            if(fontImage.pixels[i] == 0xff0000ff)
                numberOfUnicodes++;
        }
        
        offsets = new int[numberOfUnicodes];
        widths = new int[numberOfUnicodes];
        
        int unicode = 0;
        
        for(int i = 0; i < fontImage.width; i++)
        {
            if(fontImage.pixels[i] == 0xff0000ff)
            {
                offsets[unicode] = i;
            }
            
            if(fontImage.pixels[i] == 0xffffff00)
            {
                widths[unicode] = i - offsets[unicode];
                unicode++;
            }
        }
        
        for(int i = 0; i < numberOfUnicodes; i++)
        {
            avgCharWidth += widths[i];
        }
        avgCharWidth /= numberOfUnicodes;
    }
    
    public int getNumberOfUnicodes()
    {
        return numberOfUnicodes;
    }
    
    public int getStringWidth(String text)
    {
        int res = 0;
        for(int i = 0; i < text.length(); i++)
        {
            int unicode = text.codePointAt(i);
            if(unicode >= numberOfUnicodes)
                unicode = 0;
            res += widths[unicode];
        }
        return res;
    }
    
    public int getAvgCharWidth()
    {
        return avgCharWidth;
    }
}
