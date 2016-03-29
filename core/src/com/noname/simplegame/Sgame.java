package com.noname.simplegame;

import com.badlogic.gdx.Game;
import com.noname.simplegame.screens.MainMenuScreen;


public class Sgame extends Game {
	public MainMenuScreen game;

	@Override
	public void create () {
		game = new MainMenuScreen(this);
		setScreen(game);
	}


}
