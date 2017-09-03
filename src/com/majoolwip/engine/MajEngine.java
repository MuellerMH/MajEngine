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

/**
 *
 * @author majoolwip
 */
public class MajEngine 
{
    private static Window window;
    
    private static volatile boolean running = false; //Control the games main loop
    private static double FRAME_CAP = 1.0 / 60.0;    //Time frame for 60 frames every one second.
    
    public MajEngine()
    {
        
    }
    
    public void start()
    {
        
    }
    
    public void stop()
    {
        
    }
    
    public void run()
    {
        
    }
    
    public void cleanUp()
    {
        
    }

    public static Window getWindow()
    {
        return window;
    }

    public static boolean isRunning()
    {
        return running;
    }
    
    
}
