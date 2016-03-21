package com.noname.simplegame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.noname.simplegame.model.AssetLoader;

/**
 * Created by anton on 14.03.2016.
 */
public class MainMenuScreen implements Screen {
    private final Game game;
    private Stage stage;
    private Table table;
    private TextButton play;
    private TextButton exit;


    public MainMenuScreen(final Game game) {
        this.game = game;
        this.game.setScreen(this);


    }

    @Override
    public void show() {
        AssetLoader.mainMenu();
        table = new Table();
        table.setFillParent(true);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        TextButtonStyle tbs = new TextButtonStyle();
        tbs.font = new BitmapFont();
        tbs.up = new NinePatchDrawable(AssetLoader.buttonUp);
        tbs.down = new NinePatchDrawable(AssetLoader.buttonDown);
        play = new TextButton("Play", tbs);
        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                new GameScreen(game);
            }
        });
        table.center();
        //table.setDebug(true);
        AssetLoader.load();
        Image bike = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("data/bikePrew.png")))));
        ImageButton.ImageButtonStyle ibs = new ImageButton.ImageButtonStyle(null, null,
                null,AssetLoader.previous, AssetLoader.previous, null);

        ImageButton previous = new ImageButton(ibs);
        table.add(previous).left().width(64f);
        table.add(bike).center().expandY().width(300f).bottom().padBottom(100f);
        ImageButton.ImageButtonStyle ibs2 = new ImageButton.ImageButtonStyle(null, null,
                null,AssetLoader.next, AssetLoader.next, null);
        ImageButton next = new ImageButton(ibs2);
        table.add(next).right().width(64f);
        table.row();
        exit = new TextButton("exit", tbs);
        exit.setWidth(30f);
        stage.addActor(table);
        table.setBackground(new NinePatchDrawable(AssetLoader.head));
        table.add(play).expand(true,false).top().left().width(200f).height(50f);
        table.add().width(100f);
        table.add(exit).expand(true,false).top().right().width(200f).height(50f);


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
        stage.getViewport().update(width, height, true);
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
