package com.mygdx.game;


import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.Vector;
import java.util.List;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Дмитрий on 30.01.2015.
 */

public class Board {  // board [0-24][0-24]
    public ArrayList<Cage> cage = new ArrayList<Cage>();
    protected boolean init = false;
    public Board() {
        //while (!getc(0, 24).path) {  // Если не инициализировано, то иниц
        if (!init)
            initboard();
        //}
    }

    public void findpath (int i, int j) {
        getc(i, j).path = true;
        if (i > 0 && getc(i, j).left && !getc(i - 1, j).path) {  // left
            Gdx.app.log("Cage of path ", "cage["+(i-1)+"; "+j+"]");
            findpath(i-1, j);
        }
        if (j < 24 && getc(i, j).up && !getc(i, j + 1).path) {  // up
            Gdx.app.log("Cage of path ", "cage["+i+"; "+(j+1)+"]");
            findpath(i, j+1);
        }
        if (i < 24 && getc(i, j).right && !getc(i + 1, j).path) {  // right
            Gdx.app.log("Cage of path ", "cage["+(i+1)+"; "+j+"]");
            findpath(i+1, j);
        }
        if (j > 0 && getc(i, j).down && !getc(i, j - 1).path) {  // down
            Gdx.app.log("Cage of path ", "cage["+i+"; "+(j-1)+"]");
            findpath(i, j-1);
        }
    }

    protected void initboard() {
        int i, j;
        for (i = 0; i < 25; i++)
            for (j = 0; j < 25; j++)
                cage.add (new Cage());
        setwalls();
        findpath(24,0);
        if (!getc(0, 24).path) {
            cage.clear();
            initboard();
        }
        init = true;
    }

    public void setwalls () {
        int i, j;
        for (i = 0; i < 25; i++)
            for (j = 0; j < 25; j++) { // right and down
                    if (i > 0 && getc(i-1, j).right == false) { // Если из левой клетки нельзя пройти в правую, то и из правой в лево нельзя
                        getc(i, j).left = false;
                    }
                    if (j < 24 && getc(i, j+1).down == false) {  // up
                        getc(i, j).up = false;
                    }
            }
        for (i = 0; i < 25; i++) {
            getc(i, 0).down = false;
            getc(i, 24).up = false;
            getc(0, i).left = false;
            getc(24, i).right = false;
        }
    }

    public Cage getc(int x, int y) {
        return cage.get(y*25 + x);
    }


}
