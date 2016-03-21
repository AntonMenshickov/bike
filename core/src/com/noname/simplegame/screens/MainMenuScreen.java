package com.noname.simplegame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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
        AssetLoader.load();

        table = new Table();
        table.setFillParent(true);
        table.center();
        table.setBackground(new NinePatchDrawable(AssetLoader.head));

        stage = new Stage(new ScreenViewport());
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);

        TextButtonStyle tbs = new TextButtonStyle();
        tbs.font = new BitmapFont();
        tbs.up = AssetLoader.buttonUp;
        tbs.down = AssetLoader.buttonDown;
        play = new TextButton("Play", tbs);
        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeListener.ChangeEvent event, Actor actor) {
                new GameScreen(game);
            }
        });


        ImageButton settings = new ImageButton(new ImageButton.ImageButtonStyle(null, null,
                null,AssetLoader.settings, AssetLoader.settings, null));

        Image bike = new Image(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("data/bikePrew.png")))));

        ImageButton previous = new ImageButton(new ImageButton.ImageButtonStyle(null, null,
                null,AssetLoader.previous, AssetLoader.previous, null));


        ImageButton next = new ImageButton(new ImageButton.ImageButtonStyle(null, null,
                null,AssetLoader.next, AssetLoader.next, null));

        exit = new TextButton("exit", tbs);
        exit.setWidth(30f);


        table.add().expandX();
        table.add().expandX();
        table.add(settings).right().padRight(5f).padTop(5f);
        table.row();

        table.add(previous).left().width(67f);
        table.add(bike).center().expandY().bottom().padBottom(100f);

        table.add(next).right().width(67f);
        table.row();

        table.add(play).expand(true, false).top().left();
        table.add().width(100f);
        table.add(exit).expand(true,false).top().right();


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
