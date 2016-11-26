package be.haraka.game2;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Game;
import be.haraka.game2.screens.Play;


public class GameClass extends Game 
{
	public Screen play = new Play();

	public void create () 
	{
		setScreen(play);
	}

	public void render () 
	{
		super.render();
	}
	
	public void dispose () 
	{
		super.dispose();
	}
	
	public void resize(int width,int height)
	{
		super.resize(width, height);
	}
	
	public void pause()
	{
		super.pause();
	}
	
	public void resume()
	{
		super.resume();
	}
	
}
