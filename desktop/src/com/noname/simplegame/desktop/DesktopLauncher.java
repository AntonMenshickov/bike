package com.noname.simplegame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import com.noname.simplegame.Sgame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		TexturePacker.process("../assets/data/menu", "../assets/data/menu", "menu");
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Sgame(), config);
	}
}
