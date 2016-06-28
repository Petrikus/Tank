package com.petrikus.Game;

import com.petrikus.Graphicks.Sprite;
import com.petrikus.Graphicks.SpriteSheet;
import com.petrikus.Graphicks.Texture_Atlass;
import com.petrikus.IO.Input;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Petrikus on 22.06.2016.
 */
public class Player extends Entity{

    public static final int SPRITE_SCALE = 16;
    public static final int SPRITES_PER_HEAD = 1;
    private enum Head{
        NORTH(0 * SPRITE_SCALE, 0*SPRITE_SCALE, SPRITE_SCALE, SPRITE_SCALE),
        EAST(6 * SPRITE_SCALE, 0*SPRITE_SCALE, SPRITE_SCALE, SPRITE_SCALE),
        WEST(2 * SPRITE_SCALE, 0*SPRITE_SCALE, SPRITE_SCALE, SPRITE_SCALE),
        SOUTH(4 * SPRITE_SCALE, 0*SPRITE_SCALE, SPRITE_SCALE, SPRITE_SCALE);

        private int x,y,h,w;

        Head(int x, int y, int h, int w)
        {
            this.x = x;
            this.y = y;
            this.h = h;
            this.w = w;
        }
        protected BufferedImage texture(Texture_Atlass atlass){
              return atlass.cut(x,y,w,h);      }
    }

    private Head head;
    private Map<Head, Sprite> spriteMap;
    private float scale;
    private float speed;
    public Player(float x, float y, float scale,float speed, Texture_Atlass atlass){
        super(EntityType.Player,x,y);

        head = Head.NORTH;
        spriteMap = new HashMap<Head, Sprite>();
        this.scale = scale;
        this.speed = speed;
        for (Head h : Head.values()){
            SpriteSheet sheet = new SpriteSheet(h.texture(atlass), SPRITES_PER_HEAD, SPRITE_SCALE);
            Sprite sprite = new Sprite(sheet, scale);
            spriteMap.put(h, sprite);
        }
    }
    @Override
   public void update(Input in) {
        float newX = x;
        float newY = y;
        if (in.getKey(KeyEvent.VK_UP))
        {
            newY -= speed;
            head = Head.NORTH;
        }
        else if(in.getKey(KeyEvent.VK_RIGHT))
        {
            newX += speed;
            head = Head.EAST;
        }
        else if (in.getKey(KeyEvent.VK_DOWN))
        {
            newY += speed;
            head = Head.SOUTH;
        }
        else if(in.getKey(KeyEvent.VK_LEFT))
        {
            newX -= speed;
            head = Head.WEST;
        }
        if (newX < 0)
        {
            newX = 0;
        }
        else if (newX >= Game.WIDTH - SPRITE_SCALE*scale)
        {
            newX = Game.WIDTH - SPRITE_SCALE*scale;
        }

        if (newX < 0)
        {
            newY = 0;
        }
        else if (newY >= Game.HEIGHT - SPRITE_SCALE*scale)
        {
            newY = Game.HEIGHT - SPRITE_SCALE*scale;
        }

        x = newX;
        y = newY;
   }

    @Override
    public void render(Graphics2D g) {
        spriteMap.get(head).render(g, x, y);
    }
}
