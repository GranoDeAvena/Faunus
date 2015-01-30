package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * Created by Дмитрий on 30.01.2015.
 */
public class MyButtons {
    public int x;
    public int y;
    public int wight;
    public int hight;
    public int state = 0;

    public MyButtons (int x1, int y1, int hight1, int wight1){
        x = x1;
        y = y1;
        hight = hight1;
        wight = wight1;
    }
    public MyButtons (int x1, int y1){
        x = x1;
        y = y1;
        hight = 50;
        wight = 240;
    }
    public boolean contein (int x1, int y1) {
        if (x1 > x && x1 < x + wight && y1 > y && y1 < y + hight)
            return true;
        else
            return false;
    }
    public boolean contein (Vector2 point) {
        if (point.x > x && point.x < x + wight && point.y > y && point.y < y + hight)
            return true;
        else
            return false;
    }
}
