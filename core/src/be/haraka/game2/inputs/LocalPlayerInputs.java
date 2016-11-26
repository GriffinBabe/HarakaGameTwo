package be.haraka.game2.inputs;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import be.haraka.game2.screens.Play;
import be.haraka.game2.entities.LocalPlayer;
import be.haraka.game2.map.MapUtil;

public class LocalPlayerInputs implements InputProcessor 
{
	
	public LocalPlayerInputs()
	{
	}

	
	public boolean keyDown(int keycode) 
	{
		switch(keycode)
		{
		case Keys.Z:
			LocalPlayer.velocity.y = LocalPlayer.speed;
			break;
		case Keys.Q:
			LocalPlayer.velocity.x = - LocalPlayer.speed;
			break;
		case Keys.D:
			LocalPlayer.velocity.x = LocalPlayer.speed;
			break;
		case Keys.S:
			LocalPlayer.velocity.y = -LocalPlayer.speed;
			break;
			
		case Keys.SPACE:
			//Toggle boolean trick
			LocalPlayer.playerCenteredCamera = !LocalPlayer.playerCenteredCamera;
		}

		return true;
	}


	public boolean keyUp(int keycode) 
	{
		switch(keycode)
		{
		case Keys.Z:
			LocalPlayer.velocity.y = 0;
			break;
		case Keys.Q:
			LocalPlayer.velocity.x = 0;
			break;
		case Keys.D:
			LocalPlayer.velocity.x = 0;
			break;
		case Keys.S:
			LocalPlayer.velocity.y = 0;
			break;
		}
		return true;
	}


	public boolean keyTyped(char character) 
	{
		return false;
	}


	public boolean touchDown(int screenX, int screenY, int pointer, int button) 
	{
        if (button == Buttons.LEFT){
        	LocalPlayer.pos.x = 0;
        	LocalPlayer.pos.y = 0;
        	System.out.println(MapUtil.gameToIso(LocalPlayer.pos).x + " " + MapUtil.gameToIso(LocalPlayer.pos).y);
        }
        
        if(button == Buttons.RIGHT){
        	LocalPlayer.pos.x = screenX-LocalPlayer.texture.getWidth()/2;
        	LocalPlayer.pos.y = Gdx.graphics.getHeight()/2-screenY-LocalPlayer.texture.getHeight()/4;
        	System.out.println(MapUtil.gameToIso(LocalPlayer.pos).x + " " + MapUtil.gameToIso(LocalPlayer.pos).y);
        	System.out.println(LocalPlayer.texture.getHeight());
        	System.out.println(LocalPlayer.texture.getWidth());
            
            
        }
		return false;
	}


	public boolean touchUp(int screenX, int screenY, int pointer, int button) 
	{
		return false;
	}


	public boolean touchDragged(int screenX, int screenY, int pointer) 
	{
		return false;
	}


	public boolean mouseMoved(int screenX, int screenY)
	{

		return false;
	}


	public boolean scrolled(int amount) 
	{
		return false;
	}

}
