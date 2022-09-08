package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.game.Main;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		//my config
		config.setTitle("Flappy Bird");
		config.setWindowedMode(1000,500);

		config.setForegroundFPS(60);
		//config.setTitle("flappyBirdClone");
		new Lwjgl3Application(new Main(), config);
	}
}
