package com.mygdx.game;

import com.badlogic.gdx.Gdx;

/**
 * Created by Дмитрий on 30.01.2015.
 */
public class Cage {
    public int state;
    public int x;
    public int y;
    public int num;
    public boolean isinit = false;
    public boolean path = false;
    public boolean up = true;  // Могу пройти, нет стены
    public boolean right = true;
    public boolean down = true;
    public boolean left = true;
    public Cage () {
        if (!isinit)
            init(state);
    }
    public void init (int n) {
        num = n;
        state =  (int)(Math.random()*5); //
        int wc = 20; // Ширина клетки
        x = wc + n%25*wc*n;
        y = 60 + wc + n/25*wc;
        if (state == 1) right = false;
        if (state == 2) right = false;
        if (state == 3) down = false;
        if (state == 4) down = false;

//        switch (state) {
//            case 1: right = false;
//            case 2: right = false;
//            case 3: down = false;
//            case 4: down = false;
//            case 5: {
//                right = false;
//                down = false;
//            }
//        }
        isinit = true;
    }
}

