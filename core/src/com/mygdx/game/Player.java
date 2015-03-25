package com.mygdx.game;

/**
 * Created by Дмитрий on 23.03.2015.
 */
public class Player {
    public int x, y;
    public int bx, by;
    public Player (int i, int j, int wc) {
        bx = i; // 24
        by = j;  // 0
        x = bx*wc + 20;   // 500
        y = by*wc + 400;   // 400
    }
}
