package com.petrikus.Graphicks;

import java.awt.image.BufferedImage;

/**
 * Created by Petrikus on 22.06.2016.
 */
public class SpriteSheet {

    private BufferedImage sheet;
    private int spriteCount;
    private int scale;
    private int spriteInWidth;

    public SpriteSheet(BufferedImage sheet, int spriteCount, int scale)
    {
        this.sheet = sheet;
        this.spriteCount = spriteCount;
        this.scale = scale;
        this.spriteInWidth = sheet.getWidth()/scale;
    }

    public BufferedImage getSprite(int index)
    {
        index = index% spriteCount;
        int x = index % spriteInWidth * scale;
        int y = index / spriteInWidth * scale;
        return sheet.getSubimage(x,y,scale,scale);
    }
}
