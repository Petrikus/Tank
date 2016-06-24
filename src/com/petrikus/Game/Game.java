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
    private SpriteSheet sheet;
    private Sprite sprite;
    float x = 350;
    float k = 250;
    int zet = 0;
    float speed = 4;
    public Game(){
        running = false;
        Display.create(WIDTH,HEIGHT,TITLE,CLEAR_COLOR,NUM_BUFFER);
        graphics2D = Display.getGraphics();
        input = new Input();
        Display.addInput(input);
        imageAtlass = new Texture_Atlass(ATLAS_FILE_NAME);
        sheet = new SpriteSheet(imageAtlass.cut(0*16, 0*16, 32, 16), 2, 16);
        sprite = new Sprite(sheet, 2);
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

        if (input.getKey(KeyEvent.VK_D))
        {
           x += speed;

        }
        if (input.getKey(KeyEvent.VK_S))
        {
            k = k + speed;


        }
        if (input.getKey(KeyEvent.VK_A))
        {
            x -= speed;


        }
        if (input.getKey(KeyEvent.VK_W))
        {
            k -= speed;
           

        }

    }

    private void Render(){
        Display.clear();
        sprite.render(graphics2D, x, k);
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
