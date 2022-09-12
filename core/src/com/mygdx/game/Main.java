package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
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
    float time = 2f;
    float timePeriod = 3f;
    Pipe hitPipe = null;
    enum Screen {
        TITLE, MAIN_GAME, GAME_OVER;
    }
    Screen currentScreen = Screen.TITLE;
    BitmapFont font;

    @Override
    public void create () {
        batch = new SpriteBatch();
        font = new BitmapFont();
        img = new Texture("bird.png");
        pImg = new Texture("pipe.png");
        player = new Player(img);
        //Pipe pipe = new Pipe(pImg);
        generator = new Generator(pImg, WINDOW_WIDTH, WINDOW_HEIGHT);

        camera = new OrthographicCamera();
        viewport = new FitViewport(WINDOW_WIDTH, WINDOW_HEIGHT, camera);
        colision = new Colision();
        Gdx.input.setInputProcessor( new InputAdapter() {
            @Override
            public boolean keyDown (int keyCode) {

                if(currentScreen == Screen.TITLE && keyCode == Keys.SPACE){
                    currentScreen = Screen.MAIN_GAME;
                    hitPipe = null;
                    player.jumpSpeed = 275f;
                    player.speed = 100f;
                    timePeriod = 3f;
                    player.position.y = 0;
                }
                else if(currentScreen == Screen.GAME_OVER && keyCode == Keys.ENTER){
                    currentScreen = Screen.TITLE;
                    clearPipes();

                }

                return true;
            }

        });

    }

    @Override
    public void render () {

        if(currentScreen == Screen.TITLE){
            Gdx.gl.glClearColor(0, .25f, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
            batch.begin();
            batch.setProjectionMatrix(camera.combined);
            font.draw(batch, "Tweet Bird", Gdx.graphics.getWidth()*(-1)*.05f, Gdx.graphics.getHeight()*(1)*.2f);
            font.draw(batch, "Skacz aby wygrac!", Gdx.graphics.getWidth()*(-1)*.05f, Gdx.graphics.getHeight()*(1)*.1f);
            font.draw(batch, "Nacisnij spacje", Gdx.graphics.getWidth()*(-1)*.05f, Gdx.graphics.getHeight()*(-1)*.25f);
            batch.end();
        }
        else if(currentScreen == Screen.MAIN_GAME){
            ScreenUtils.clear(0f, 0.19f, .75f, 1);
            batch.begin();

            player.Draw(batch);
            //System.out.println(pipes.get(0).position.x);
            for ( Pipe pipe : pipes) {

                pipe.Draw(batch);
                if (colision.Colide(player.rectangle, pipe.rectangle)){
                    //System.out.println("Kolizja");
                    pipe.speed = 0f;
                    hitPipe = pipe;

                    player.speed = 0f;
                    player.jumpSpeed = 0f;
                    currentScreen = Screen.GAME_OVER;
                    timePeriod = 9999f;
                    clearPipes();
                    break;
                }
                if (pipe.position.x< (-600) || hitPipe != null) remPipes.add(pipe);
            }
            time += Gdx.graphics.getDeltaTime();
            if (time > timePeriod) {
                time = 0f;
                remPipe();
            }
            if ( player.position.y + player.sprite.getHeight() > WINDOW_HEIGHT/2 || player.position.y < -1*WINDOW_HEIGHT/2 ) {
                stopPipes();
                clearPipes();
                currentScreen = Screen.GAME_OVER;
            }

            batch.setProjectionMatrix(camera.combined);

            batch.end();
        }
        else if(currentScreen == Screen.GAME_OVER){
            ScreenUtils.clear(.75f, 0f, .2f, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            batch.begin();
            font.draw(batch, "Przegrana!", Gdx.graphics.getWidth()*.25f, Gdx.graphics.getHeight() * .25f);
            font.draw(batch, "Press enter to restart.", Gdx.graphics.getWidth()*(-1)*.25f, Gdx.graphics.getHeight() * .25f);
            batch.end();
        }

    }

    private void remPipe() {
        for ( Pipe pipe : remPipes) {
            pipes.remove(pipe);
        }
        remPipes.clear();
        generatePipes();
        System.out.println(pipes.size());

    }

    private void generatePipes() {
        for( Pipe pipe : generator.getPipes()){
            pipes.add(pipe);
        }
    }

    private void clearPipes() {
        pipes.clear();
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
