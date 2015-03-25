package com.mygdx.game;

import com.badlogic.gdx.Gdx;

/**
 * Created by Дмитрий on 30.01.2015.
 */
public class Cage {
    public int state;
    public boolean path = false;
    public boolean up = true;  // Могу пройти, нет стены
    public boolean right = true;
    public boolean down = true;
    public boolean left = true;
    public Cage () {
        init(state);
    }
    public void init (int n) {
        state =  (int)(Math.random()*6); //
        switch (state) {
            case 1: left = false; break;
            case 2: left = false; break;
            case 3: up = false; break;
            case 4: up = false; break;
            case 5: {
                left = false;
                up = false;
                break;
            }
        }
    }
}

