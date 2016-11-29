package be.haraka.game2.screens;

import javax.swing.JOptionPane;

//Local Engine
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;

import be.haraka.game2.entities.LocalPlayer;
import be.haraka.game2.inputs.CameraManager;
import be.haraka.game2.map.MapManager;
import be.haraka.game2.net.packets.Packet01Disconnect;


public class Play implements Screen {
	
	//Local engine elements
	public IsometricTiledMapRenderer renderer;
	public OrthographicCamera camera;
	public LocalPlayer localPlayer;
	private MapManager mapManager;
	private CameraManager cameraManager;
	private Batch batch;
	private BitmapFont font;
	private static String localPlayerName;

	//Engine loop
	public void render(float delta)
	{
		
		if (!localPlayer.isPlayerConnected){
			System.out.println("Quitting game due to disconnection");
			Gdx.app.exit();
		}
		
		//Goes to localPlayer loop part
		localPlayer.update(delta);
		
		//Graphics Configuration
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Render map
		mapManager.render(camera);
		cameraManager.cameraUpdate(delta, camera, localPlayer);
		
		//Render Players
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		batch.draw(localPlayer.texture,localPlayer.pos.x,localPlayer.pos.y);
		font.draw(batch, localPlayerName, localPlayer.pos.x-25, localPlayer.pos.y+localPlayer.texture.getHeight()+20);
		batch.end();

	}

	public void resize(int width, int height)
	{
		cameraManager.cameraResize(width, height,camera);
	}
	
	public void show()
	{
		
		//Creating map, camera and entities
		batch = new SpriteBatch();
		mapManager = new MapManager();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		cameraManager = new CameraManager();
		
		localPlayerName = JOptionPane.showInputDialog("What is your username?");
		localPlayer = new LocalPlayer(localPlayerName);
		
		font = new BitmapFont(Gdx.files.internal("Sources/fonts/font1.fnt"));
		Gdx.input.setInputProcessor(localPlayer);

	}
	
	public void hide()
	{
		dispose();
	}
	
	public void pause()
	{
		
	}
	
	public void resume()
	{
		
	}
	
	public void dispose()
	{
		if(JOptionPane.showConfirmDialog(null, "Sure u wanna leave kiddo?") == JOptionPane.YES_OPTION){
			localPlayer.isPlayerConnected = false;
			Packet01Disconnect disconnectPacket = new Packet01Disconnect(localPlayer.username);
			disconnectPacket.writeData(localPlayer.socketClient);
		}
		
	}


}
