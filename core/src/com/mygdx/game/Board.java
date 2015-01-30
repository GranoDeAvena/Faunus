package com.mygdx.game;

import java.util.Vector;

import java.util.List;

/**
 * Created by Дмитрий on 30.01.2015.
 */
public class Board {
//    public Cage c[] = new Cage[625];
    public Vector<Cage> c = new Vector<Cage>();
    public Board () {
//        c[625] = new Cage();
//        c[1].init(1);
        for (int i = 1; i < 625; i++)
            c.add(new Cage());
        c.get(1).init(1);
    }
}
