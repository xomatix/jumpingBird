package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

public class Generator {

    Texture img;
    Pipe bPipe;
    Pipe tPipe;
    Float speed = 500f;
    int sWidth;
    int sHeight;

    public Generator(Texture pImg, int width, int height){
        img = pImg;
        sWidth = width;
        sHeight = height;
    }

    public ArrayList<Pipe> getPipes(){
        bPipe = new Pipe(img);
        tPipe = new Pipe(img);
        //tPipe.sprite.setOriginCenter(tPipe.sprite.getWidth()/2, tPipe.sprite.getHeight()/2);
        //tPipe.sprite.setOriginCenter();
        tPipe.sprite.setRotation(180f);
        tPipe.sprite.flip(true,false);

        //calculate position of the pipes
        // - 200 minimum, +80 maximum //

        int y = (int) (Math.random() * 280 + 1) - 200;
        System.out.println(y);

        bPipe.position.y += y;
        bPipe.position.x = sWidth/2;
        tPipe.position.y += tPipe.sprite.getHeight() + y + 150;
        tPipe.position.x = sWidth/2;


        ArrayList<Pipe> pipes = new ArrayList<>();
        pipes.add(bPipe);
        pipes.add(tPipe);
        return pipes;
    }
}
