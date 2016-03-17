package com.noname.simplegame;

import com.badlogic.gdx.Game;
import com.noname.simplegame.screens.MainMenuScreen;

public class Sgame extends Game {

    public MainMenuScreen game;

    @Override
    public void create() {

        game = new MainMenuScreen(this);
        setScreen(game);
    }

		/*@Override
        public void resize(int width, int height) {
			game.resize(width, height);
		}
	
		/*@Override
		public void render() {
			game.render(1/60f);
		}*/

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
