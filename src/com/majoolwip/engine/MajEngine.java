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

import java.awt.event.KeyEvent;

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
    private static boolean debug = false;
    
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
            frameTime += passedTime;
            
            while(unprocessedTime >= FRAME_CAP)
            {
                unprocessedTime -= FRAME_CAP;
                render = true;
                
                if(input.isKeyUp(KeyEvent.VK_F1))
                {
                    debug = !debug;
                }
                
                state.update((float)FRAME_CAP);
                input.update();
                
                if(frameTime >= 1.0)
                {
                    frameTime = 0;
                    fps = frames;
                    frames = 0;
                }
            }
            
            if(render)
            {
                renderer.clear();
                state.render(renderer);
                if(debug)
                    renderer.drawString("Fps-" + fps, 0xffffffff, 0, 0);
                window.update();
                frames++;
                
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
        
        cleanUp();
    }
    
    public void cleanUp()
    {
        window.dispose();
    }

    public static Window getWindow()
    {
        return window;
    }

    public static boolean isRunning()
    {
        return running;
    }

    public static Renderer getRenderer() {
        return renderer;
    }

    public static Input getInput() {
        return input;
    }

    public static State getState() {
        return state;
    }
    
    
    
    
}
