package be.haraka.game2.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import be.haraka.game2.GameClass;

public class DesktopLauncher {
	public static void main (String[] arg) {
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		//cfg.useGL20 = true;
		cfg.width =1280;
		cfg.height = 720;
		cfg.resizable = true;
		
		new LwjglApplication(new GameClass(), cfg);
		
		
	}
}
