package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;

public class Main extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    Texture pImg;
    Player player;
    Generator generator;
    ArrayList<Pipe> pipes = new ArrayList<>();
    ArrayList<Pipe> remPipes = new ArrayList<>();
    private Viewport viewport;
    private OrthographicCamera camera;
    int WINDOW_WIDTH = 1000;
    int WINDOW_HEIGHT = 500;
    Colision colision;
    //timer period for spawning
    float time = 0f;
    float timePeriod = 999f;
    Pipe hitPipe = null;

    @Override
    public void create () {
        batch = new SpriteBatch();
        img = new Texture("bird.png");
        pImg = new Texture("pipe.png");
        player = new Player(img);
        //Pipe pipe = new Pipe(pImg);
        generator = new Generator(pImg, WINDOW_WIDTH, WINDOW_HEIGHT);
        for( Pipe pipe : generator.getPipes()){
            pipes.add(pipe);
        }
        camera = new OrthographicCamera();
        viewport = new FitViewport(WINDOW_WIDTH, WINDOW_HEIGHT, camera);
        System.out.println(pipes);

        colision = new Colision();
    }

    @Override
    public void render () {
        ScreenUtils.clear(0, 0.4f, 1, 1);
        batch.begin();

        player.Draw(batch);
        for ( Pipe pipe : pipes) {
            pipe.Draw(batch);
            if (colision.Colide(player.rectangle, pipe.rectangle)){
                //System.out.println("Kolizja");
                timePeriod = 999f;
                pipe.speed = 0f;
                hitPipe = pipe;
                clearPipes();
                player.speed = 0f;
                break;
            }
            if (pipe.position.x< (-600) || hitPipe != null) remPipes.add(pipe);
        }
        time += Gdx.graphics.getDeltaTime();
        if (time > timePeriod) {
            time = 0f;
            remPipe();
        }
        if ( player.position.y + player.sprite.getHeight() > WINDOW_HEIGHT/2 || player.position.y < -1*WINDOW_HEIGHT/2 ) stopPipes();

        batch.setProjectionMatrix(camera.combined);

        batch.end();
    }

    private void remPipe() {
        for ( Pipe pipe : remPipes) {
            pipes.remove(pipe);
        }
        remPipes.clear();
        for (Pipe p : generator.getPipes()) {
            pipes.add(p);
        }
        System.out.println(pipes.size());

    }

    private void clearPipes() {
        for ( Pipe pipe : remPipes) {
            if ( hitPipe != pipe) pipes.remove(pipe);
        }
    }

    private void stopPipes() {
        for ( Pipe pipe : pipes) {
            pipe.speed = 0f;
            timePeriod = 999f;
        }
        System.out.println("Dotytka barierki");
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width,height);
    }

    @Override
    public void dispose () {
        batch.dispose();
        img.dispose();
    }
}
