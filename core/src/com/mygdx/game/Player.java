package com.mygdx.game;

/**
 * Created by Дмитрий on 23.03.2015.
 */
public class Player {
    public int x, y;
    public int bx, by;
    public Player (int i, int j, int wc) {
        bx = i;
        by = j;
        x = bx*wc + 60;
        y = by*wc + 550;
    }
}
