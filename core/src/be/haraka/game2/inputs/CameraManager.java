package be.haraka.game2.inputs;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

import be.haraka.game2.entities.LocalPlayer;
import be.haraka.game2.screens.Play;

public class CameraManager
{
	public void cameraUpdate(float delta,OrthographicCamera camera,LocalPlayer localPlayer)
	{
		//Screen Borders camera scroll
		if (!localPlayer.playerCenteredCamera)
		{
			if (Gdx.input.getX() < 50)
			{
				camera.translate(-8,0,0);
			}
			
			else if (Gdx.input.getX() > Gdx.graphics.getWidth() - 50)
			{
				camera.translate(8,0,0);
			}
			
			if (Gdx.input.getY() < 50)
			{
				camera.translate(0,8,0);
			}
			
			else if (Gdx.input.getY() > Gdx.graphics.getHeight() - 50)
			{
				camera.translate(0,-8,0);
			}
		}
		
		else
		{
			camera.position.set(localPlayer.pos.x,localPlayer.pos.y,0);
		}
		
		camera.update();
	}
	
	public void cameraResize(int width, int height,OrthographicCamera camera)
	{
		camera.viewportWidth = width;
		camera.viewportHeight = height;
		camera.position.set(Gdx.graphics.getWidth()/2,0,0);
		camera.update();
	}
	
}
