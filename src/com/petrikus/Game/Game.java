package com.petrikus.Game;

import com.petrikus.Graphicks.Sprite;
import com.petrikus.Graphicks.SpriteSheet;
import com.petrikus.Graphicks.Texture_Atlass;
import com.petrikus.IO.Input;
import com.petrikus.display.Display;
import com.petrikus.utils.Time;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Petrikus on 21.06.2016.
 */
public class Game implements Runnable{
    public static final int WIDTH = 800;
    public static final int HEIGHT = 640;
    public static final String TITLE = "Tanks";
    public static final int CLEAR_COLOR = 0xff000000;
    public static final int NUM_BUFFER = 3;
    public static final String ATLAS_FILE_NAME = "texture_atlas.png";
    public static final float updateRate = 60.0f;
    public static final float updateInterval = Time.SECOND / updateRate;
    public static final long idleTime = 1;

    private boolean running ;
    private Thread gameThread;
    private Graphics2D graphics2D;
    private Input input;
    private Texture_Atlass imageAtlass;
   private  Player player;

    public Game(){
        running = false;
        Display.create(WIDTH,HEIGHT,TITLE,CLEAR_COLOR,NUM_BUFFER);
        graphics2D = Display.getGraphics();
        input = new Input();
        Display.addInput(input);
        imageAtlass = new Texture_Atlass(ATLAS_FILE_NAME);
        player = new Player(300, 300, 2, 5, imageAtlass);

    }

    public synchronized void Start(){
        if (running)
        {
            return;
        }
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

    public synchronized void Stop(){
        if (!running)
        {
            return;
        }
        running = false;
        try {

            gameThread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        cleanUp();
    }

    private void Update(){
        player.update(input);


    }

    private void Render(){
        Display.clear();
        player.render(graphics2D);
        Display.swapBuffers();
    }

    public void run(){

        int FPS = 0;
        int UPD = 0;
        float delta = 0;

        long count = 0;


        long lastTime = Time.get();
        while (running)
        {
            long nowTime = Time.get();
            long elapsedTime = nowTime - lastTime;
            lastTime = nowTime;
            count += elapsedTime;
            boolean render = false;
            delta += (elapsedTime / updateInterval);
            while (delta > 1)
            {
                Update();
                UPD++;
                delta--;
                render = true;
            }
            if (render)
            {
                Render();
                FPS++;
            }
            else
            {
                try {
                    Thread.sleep(idleTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (count >= Time.SECOND)
            {
                Display.setTitle(TITLE + "|| FPS: " + FPS + " ||UPD: " + UPD);
                UPD = FPS = 0;
                count = 0;
            }

        }
    }

    private void cleanUp(){
        Display.destroy();
    }
}
