package com.mygdx.game;

/**
 * Created by Дмитрий on 30.01.2015.
 */
public class Cage {
    public int state;
    public int x;
    public int y;
    public int num;
    public Cage (){
        state =  (int)(Math.random()*4);
    }
    public void init (int n) {
        //num = n;
        //x = 20 + n%25*20*n;
        //y = 60 + 20 + n/25*20;
    }
}
