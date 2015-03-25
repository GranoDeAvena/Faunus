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

public class Board {  // board [0-n][0-n]
    public ArrayList<Cage> cage = new ArrayList<Cage>();
    private boolean init = false;
    private int trynum = 1;
    private int n;
    public Board(int k) {
        n = k-1;
        initboard();
    }

    public void findpath (int i, int j) {
        getc(i, j).path = true;
        if (i > 0 && getc(i, j).left && !getc(i - 1, j).path)   // left
            findpath(i-1, j);
        if (j < n && getc(i, j).up && !getc(i, j + 1).path)   // up
            findpath(i, j+1);
        if (i < n && getc(i, j).right && !getc(i + 1, j).path)   // right
            findpath(i+1, j);
        if (j > 0 && getc(i, j).down && !getc(i, j - 1).path)   // down
            findpath(i, j-1);
    }

    protected void initboard() {
        int i, j;
        for (i = 0; i <= n; i++)
            for (j = 0; j <= n; j++)
                cage.add (new Cage());
        setwalls();
        findpath(n,0);
        if (!getc(0, n).path) {
            trynum++;
            Gdx.app.log("Reinit board! ", "try number : "+trynum);
            cage.clear();
            initboard();
        }
        init = true;
    }

    public void setwalls () {
        int i, j;
        for (i = 0; i <= n; i++)
            for (j = 0; j <= n; j++) { // right and down
                    if (i > 0 && getc(i-1, j).right == false) { // Если из левой клетки нельзя пройти в правую, то и из правой в лево нельзя
                        getc(i, j).left = false;
                    }
                    if (j < n && getc(i, j+1).down == false) {  // up
                        getc(i, j).up = false;
                    }
                if (i < n && getc(i+1, j).left == false) { // Если из левой клетки нельзя пройти в правую, то и из правой в лево нельзя
                    getc(i, j).right = false;
                }
                if (j > 0 && getc(i, j-1).up == false) {  // up
                    getc(i, j).down = false;
                }
            }
        for (i = 0; i <= n; i++) {
            getc(i, 0).down = false;
            getc(i, n).up = false;
            getc(0, i).left = false;
            getc(n, i).right = false;
        }
    }

    public Cage getc(int x, int y) {
        return cage.get(y*(n+1) + x);
    }

}
