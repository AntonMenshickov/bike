package com.noname.simplegame.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

public class AssetLoader {
    public static TextureAtlas atlas;
    public static Texture texture;
    public static Texture texture2;
    public static Texture texture3;
    public static Texture texture4;
    public static TextureRegion bikeHull, bikeWheel;
    public static NinePatch buttonUp, buttonDown, head;
    public static SpriteDrawable next, previous;
    public static TextureRegion gasUp, gasDown, breakUp, breakDown;

    public static void mainMenu() {
        atlas = new TextureAtlas(Gdx.files.internal("data/menu.atlas"));
        buttonUp = atlas.createPatch("buttonUp");
        buttonDown = atlas.createPatch("buttonDown");
        head = atlas.createPatch("head");
        next = new SpriteDrawable(atlas.createSprite ("next"));
        previous = new SpriteDrawable(atlas.createSprite("previous"));
    }

    public static void loadControls() {
        texture4 = new Texture(Gdx.files.internal("data/controls.png"));
        gasDown = new TextureRegion(texture4, 0, 0, texture4.getWidth() / 2, texture4.getHeight() / 2);
        gasDown.flip(false, false);

        gasUp = new TextureRegion(texture4, 0, texture4.getHeight() / 2, texture4.getWidth() / 2, texture4.getHeight() / 2);
        gasUp.flip(false, false);

        breakDown = new TextureRegion(texture4, texture4.getWidth() / 2, 0, texture4.getWidth() / 2, texture4.getHeight() / 2);
        breakDown.flip(false, false);

        breakUp = new TextureRegion(texture4, texture4.getWidth() / 2, texture4.getHeight() / 2, texture4.getWidth() / 2, texture4.getHeight() / 2);
        breakUp.flip(false, false);
    }

    public static void load() {

        texture = new Texture(Gdx.files.internal("data/bikeHull.png"));
        texture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        bikeHull = new TextureRegion(texture, 0, 0, texture.getWidth(), texture.getHeight());
        bikeHull.flip(false, false);

        texture2 = new Texture(Gdx.files.internal("data/bikeWheel.png"));
        texture2.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);

        bikeWheel = new TextureRegion(texture2, 0, 0, texture2.getWidth(), texture2.getHeight());
        bikeWheel.flip(false, false);


    }

    public static void dispose() {
        texture.dispose();
        texture2.dispose();
        texture3.dispose();
        atlas.dispose();
    }

}