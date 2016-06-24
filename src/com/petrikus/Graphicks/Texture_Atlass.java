package com.petrikus.Graphicks;

import com.petrikus.utils.ResursLoader;

import java.awt.image.BufferedImage;

/**
 * Created by Petrikus on 22.06.2016.
 */
public class Texture_Atlass {
    BufferedImage image;
    public Texture_Atlass(String imageName)
    {
        image = ResursLoader.loadImage(imageName);
    }

    public BufferedImage cut(int x, int y, int width, int height)
    {
        return image.getSubimage(x,y,width,height);
    }
}
