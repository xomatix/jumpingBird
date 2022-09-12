package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
    public Vector2 position;
    public Sprite sprite;
    public Rectangle rectangle;
    public float speed = 100f;
    public float jumpSpeed = 275f;
    public Player(Texture img){
        sprite = new Sprite(img);
        sprite.setOriginCenter();
        sprite.setSize(73,60);
        rectangle = new Rectangle(0,0, sprite.getWidth()*0.9f, sprite.getHeight());
        position = new Vector2(-250,0);
    }

    public void Draw(SpriteBatch batch){
        Update(Gdx.graphics.getDeltaTime());
        sprite.setPosition(position.x, position.y);
        rectangle.x = position.x;
        rectangle.y = position.y;
        System.out.println("x: " + position.x + ", y: " + position.y);
        sprite.draw(batch);
    }

    public void Update(float deltaTime){
        /* if (Gdx.input.isKeyPressed(Keys.A)) position.x-=deltaTime*speed;
        if (Gdx.input.isKeyPressed(Keys.D)) position.x+=deltaTime*speed;
        if (Gdx.input.isKeyPressed(Keys.W)) position.y+=deltaTime*speed;
        if (Gdx.input.isKeyPressed(Keys.S)) position.y-=deltaTime*speed;*/
       if (Gdx.input.isKeyPressed(Keys.W)) speed = jumpSpeed;
        position.y+=deltaTime*speed;
        speed -= 1000f*deltaTime;
    }
}
