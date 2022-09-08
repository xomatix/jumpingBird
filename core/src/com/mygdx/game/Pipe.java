package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Pipe {

    public Vector2 position;
    public Sprite sprite;
    public Rectangle rectangle;
    public float speed = 150f;

    public Pipe(Texture img){
        sprite = new Sprite(img);
        sprite.setSize(80,470);
        sprite.setOriginCenter();
        position = new Vector2(0-sprite.getWidth()/2,0-sprite.getHeight());
        rectangle = new Rectangle(position.x, position.y, sprite.getWidth(), sprite.getHeight());
        //sprite.setScale(0.3f,0.3f);
    }

    public void Draw(SpriteBatch batch){
        Update(Gdx.graphics.getDeltaTime());
        rectangle.x = position.x;
        rectangle.y = position.y;
        sprite.setPosition(position.x, position.y);
        //System.out.println("x: " + position.x + ", y: " + position.y);
        sprite.draw(batch);
    }

    public void Update(float deltaTime){
        /*if (Gdx.input.isKeyPressed(Input.Keys.A)) position.x-=deltaTime*speed;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) position.x+=deltaTime*speed;
        if (Gdx.input.isKeyPressed(Input.Keys.W)) position.y+=deltaTime*speed;
        if (Gdx.input.isKeyPressed(Input.Keys.S)) position.y-=deltaTime*speed;*/

        //automatic movement
        position.x-=deltaTime*speed;
    }
}
