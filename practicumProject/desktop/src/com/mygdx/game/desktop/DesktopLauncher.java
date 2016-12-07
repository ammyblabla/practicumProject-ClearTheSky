package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.ClearThisSky;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
//        String libPathProperty = System.getProperty("java.library.path");
//        System.out.println(libPathProperty);
		config.title = "Clear this Sky";
		config.width = ClearThisSky.WIDTH;
        config.height = ClearThisSky.HEIGHT;
		new LwjglApplication(new ClearThisSky(), config);
	}
}
