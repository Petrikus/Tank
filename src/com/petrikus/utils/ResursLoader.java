package com.petrikus.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Petrikus on 22.06.2016.
 */
public class ResursLoader {
    public static final String path = "res/";

    public static BufferedImage loadImage(String fileName)
    {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File(path + fileName));
        }catch (IOException e)
        {
            e.printStackTrace();
        }
        return image;
    }
}
