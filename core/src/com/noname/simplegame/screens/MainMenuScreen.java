package com.noname.simplegame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.noname.simplegame.model.AssetLoader;

/**
 * Created by anton on 14.03.2016.
 */
public class MainMenuScreen implements Screen, InputProcessor {
    private final Game game;
    private Stage stage;
    private Table table;
    private TextButton actor;
    private TextButton exit;

    public MainMenuScreen(final Game game) {
        this.game = game;
        this.game.setScreen(this);

        AssetLoader.loadButtons();
        stage = new Stage();
        table = new Table();
        table.setFillParent(true);
        Gdx.input.setInputProcessor(stage);
        table.setFillParent(true);
        TextButtonStyle tbs = new TextButtonStyle();
        tbs.font = new BitmapFont();
        tbs.up = new TextureRegionDrawable(AssetLoader.buttonUp);
        tbs.down = new TextureRegionDrawable(AssetLoader.buttonDown);
        actor = new TextButton("Play", tbs);
        actor.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                new GameScreen(game);
            }
        });
        exit = new TextButton("exit", tbs);
        exit.setHeight(30f);
        stage.addActor(table);
        table.add(actor).expand().top().left();
        table.add(exit).expand().top().right();
        table.row();


    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

        //stage.setViewport(new ExtendViewport(640, 480, 800, 480));
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
