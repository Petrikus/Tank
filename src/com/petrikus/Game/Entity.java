package com.petrikus.Game;

import com.petrikus.IO.Input;

import java.awt.*;

/**
 * Created by Petrikus on 22.06.2016.
 */
public abstract class Entity {
    public final EntityType type;
    protected float x;
    protected float y;
    protected Entity(EntityType type, float x, float y){
        this.type = type;
        this.x = x;
        this.y = y;
    }
    public   abstract void update(Input in);
    public abstract void render(Graphics2D g);
}
