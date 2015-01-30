package com.mygdx.game;

/**
 * Created by Дмитрий on 30.01.2015.
 */
public class Board {
    public Cage c[];

    public Board () {
        c[625] = new Cage();
        c[1].init(1);
        //for (int i = 1; i < 625; i++)
        //    c[i].init (i);
    }
}
