package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

public class Colision {

    public Colision () {
        //System.out.println(rect.overlaps(rect2));
    }

    public boolean Colide(Rectangle rectangle,Rectangle rectangle2){
        return rectangle.overlaps(rectangle2);
    }
}
