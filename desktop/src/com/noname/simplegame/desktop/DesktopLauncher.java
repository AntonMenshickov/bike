package com.noname.simplegame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.noname.simplegame.Sgame;

public class DesktopLauncher {
    //OMG WTF THIS IS SHIT COMM!!
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        new LwjglApplication(new Sgame(), config);
    }
}
