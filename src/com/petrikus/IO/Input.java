package com.petrikus.IO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

/**
 * Created by Petrikus on 21.06.2016.
 */
public class Input extends JComponent{
    private boolean[] map;

    public Input(){
        map = new boolean[256];
        for (int i = 0; i < map.length; i++)
        {
            final int Key_Code = i;
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(i,0,false), i*2);
            getActionMap().put(i * 2, new AbstractAction() {

                public void actionPerformed(ActionEvent e) {
                    map[Key_Code] = true;
                }
            });
            getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(i,0,true), i*2+1);
            getActionMap().put(i * 2+1, new AbstractAction() {

                public void actionPerformed(ActionEvent e) {
                    map[Key_Code] = false;
                }
            });
        }
    }
    public boolean[] getMap(){
        return Arrays.copyOf(map, map.length);
    }
    public boolean getKey(int keyCode){
        return map[keyCode];
    }
}
