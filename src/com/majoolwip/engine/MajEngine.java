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
public class MajEngine implements Runnable
{
    private static Window window;
    private static Renderer renderer;
    private static Input input;
    private static State state;
    
    private static volatile boolean running = false; //Control the games main loop
    private static double FRAME_CAP = 1.0 / 60.0;    //Time frame for 60 frames every one second.
    
    public MajEngine(State state)
    {
        MajEngine.state = state;
    }
    
    public void start()
    {
        window = new Window();
        renderer = new Renderer();
        input = new Input();
        
        Thread thread = new Thread(this);
        thread.run();
    }
    
    public void stop()
    {
        running = false;
    }
    
    public void run()
    {
        running = true;
        
        boolean render = false;
        
        double firstTime = 0;
        double lastTime = System.nanoTime() / 1000000000.0;
        double passedTime = 0;
        double unprocessedTime = 0;
        double frameTime = 0;
        int frames = 0;
        int fps = 0;
        
        state.init();
        
        while(running)
        {
            render = false;
            firstTime = System.nanoTime() / 1000000000.0;
            passedTime = firstTime - lastTime;
            lastTime = firstTime;
            
            unprocessedTime += passedTime;
            
            while(unprocessedTime >= FRAME_CAP)
            {
                unprocessedTime -= FRAME_CAP;
                render = true;
                
                state.update((float)FRAME_CAP);
                input.update();
            }
            
            if(render)
            {
                state.render(renderer);
                window.update();
            }
            else
            {
                try
                {
                    Thread.sleep(1);
                }
                catch(Exception e){}
            }
        }
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
