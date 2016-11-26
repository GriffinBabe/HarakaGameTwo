package be.haraka.game2.inputs;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

import be.haraka.game2.entities.LocalPlayer;
import be.haraka.game2.screens.Play;

public class CameraManager
{
	public static void cameraUpdate(float delta)
	{
		//Screen Borders camera scroll
		if (!LocalPlayer.playerCenteredCamera)
		{
			if (Gdx.input.getX() < 50)
			{
				Play.camera.translate(-8,0,0);
			}
			
			else if (Gdx.input.getX() > Gdx.graphics.getWidth() - 50)
			{
				Play.camera.translate(8,0,0);
			}
			
			if (Gdx.input.getY() < 50)
			{
				Play.camera.translate(0,8,0);
			}
			
			else if (Gdx.input.getY() > Gdx.graphics.getHeight() - 50)
			{
				Play.camera.translate(0,-8,0);
			}
		}
		
		else
		{
			Play.camera.position.set(LocalPlayer.pos.x,LocalPlayer.pos.y,0);
		}
		
		Play.camera.update();
	}
	
	public static void cameraResize(int width, int height)
	{
		Play.camera.viewportWidth = width;
		Play.camera.viewportHeight = height;
		Play.camera.position.set(Gdx.graphics.getWidth()/2,0,0);
		Play.camera.update();
	}
	
}
