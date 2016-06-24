package com.petrikus.Game;

import com.petrikus.Graphicks.Sprite;
import com.petrikus.IO.Input;

import java.awt.*;
import java.util.Map;

/**
 * Created by Petrikus on 22.06.2016.
 */
public class Player extends Entity{

    public static final int SPRITE_SCALE = 16;
    private enum Head{
        NORTH,
        EAST,
        WEST,
        SOUTH;

        private int x,y,h,w;
    }

    private Head head;
    private Map<Head, Sprite> spriteMap;
    protected Player(float x, float y){
        super(EntityType.Player,x,y);
    }
    @Override
    protected void update(Input in) {

    }

    @Override
    protected void render(Graphics2D g) {

    }
}
