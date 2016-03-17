package com.noname.simplegame.screens;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton.ImageButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.noname.simplegame.controller.WorldController;
import com.noname.simplegame.model.AssetLoader;
import com.noname.simplegame.model.MyWorld;
import com.noname.simplegame.view.WorldRenderer;


public class GameScreen implements Screen, InputProcessor {
    private final Game game;
    private MyWorld world;
    private WorldRenderer renderer;
    private WorldController controller;
    private int width;
    private int height;
    private Stage stage;
    private Table table;
    private ImageButton forward;
    private ImageButton back;
    private ImageButton buttonBreak;

    public GameScreen(final Game game) {
        this.game = game;
        game.setScreen(this);
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);
        AssetLoader.loadControls();
        ImageButtonStyle tbs = new ImageButtonStyle();
        tbs.up = new TextureRegionDrawable(AssetLoader.gasUp);
        tbs.down = new TextureRegionDrawable(AssetLoader.gasDown);
        forward = new ImageButton(tbs);
        back = new ImageButton(tbs);
        ImageButtonStyle tbs2 = new ImageButtonStyle();
        tbs2.up = new TextureRegionDrawable(AssetLoader.breakUp);
        tbs2.down = new TextureRegionDrawable(AssetLoader.breakDown);
        buttonBreak = new ImageButton(tbs2);
        table.setTransform(false);

        table.bottom();
        table.add(back).expand(true, false).bottom().left();
        table.add(buttonBreak).expand(true, false).bottom().center();
        table.add(forward).expand(true, false).bottom().right();
    }

    @Override
    public void show() {
        world = new MyWorld();
        renderer = new WorldRenderer(world);
        controller = new WorldController(world);
        //Gdx.input.setInputProcessor(stage);

    }

    @Override
    public boolean touchDragged(int x, int y, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int x, int y) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        renderer.setSize(width, height);
    }

    @Override
    public void hide() {
        //Gdx.input.setInputProcessor(null);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
        AssetLoader.dispose();
        renderer.dispose();
        world.dispose();
    }


    @Override
    public boolean keyDown(int keycode) {
        control(keycode);
        return true;
    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        if (forward.isPressed()) {
            controller.rightPressed();
            ;
        } else {
            if (back.isPressed()) {
                controller.leftPressed();
            } else {
                if (buttonBreak.isPressed()) {
                    controller.breakPressed();
                } else
                    controller.keyReset();
            }

        }
        controller.update(delta);
        renderer.render(delta);
        stage.draw();
    }

    @Override
    public boolean keyUp(int keycode) {
        controller.keyReset();
        return true;
    }

    private void control(int keycode) {
        //A - 29 D - 32 SPACE - 62
        if (keycode == 29) {
            //controller.leftPressed();
        }
        if (keycode == 32) {
            //controller.rightPressed();
        }
        if (keycode == 62) {
            controller.breakPressed();
        }

    }

    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        if (y > this.height - 100) {
            controller.breakPressed();
            return true;
        }
        if (x > this.width / 2) {
            controller.rightPressed();
            return true;
        }
        if (x < this.width / 2) {
            controller.leftPressed();
            return true;
        }
        return false;
    }

    @Override
    public boolean touchUp(int x, int y, int pointer, int button) {
        controller.keyReset();
        return true;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
