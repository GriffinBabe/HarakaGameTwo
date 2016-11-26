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
import be.haraka.game2.inputs.LocalPlayerInputs;
import be.haraka.game2.map.MapManager;
import be.haraka.game2.net.GameClient;
import be.haraka.game2.net.GameServer;
import be.haraka.game2.net.packets.Packet00Login;
import be.haraka.game2.net.packets.Packet01Disconnect;

public class Play implements Screen {
	
	//Networking elements
	private GameClient socketClient;
	private GameServer socketServer;
	
	//Local engine elements
	public static IsometricTiledMapRenderer renderer;
	public static OrthographicCamera camera;
	private LocalPlayerInputs localPlayerInputs;
	public LocalPlayer localPlayer;
	private MapManager mapManager;
	private Batch batch;
	private BitmapFont font;
	private String localPlayerName;


	

	

	
	public void render(float delta)
	{
		//Update game elements
		localPlayer.update(delta);
		
		//Graphics Configuration
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		//Render map
		mapManager.render();
		
		//Render Players
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		batch.draw(localPlayer.texture,LocalPlayer.pos.x,LocalPlayer.pos.y);
		font.draw(batch, localPlayerName, LocalPlayer.pos.x-25, LocalPlayer.pos.y+LocalPlayer.texture.getHeight()+20);
		batch.end();

	}

	public void resize(int width, int height)
	{
		CameraManager.cameraResize(width, height);
	}
	
	public void show()
	{
		
		//Creating map, camera and entities
		batch = new SpriteBatch();
		mapManager = new MapManager();
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		
		localPlayerName = JOptionPane.showInputDialog("What is your username?");
		localPlayer = new LocalPlayer(localPlayerName);
		
		font = new BitmapFont(Gdx.files.internal("Sources/fonts/font1.fnt"));
		localPlayerInputs = new LocalPlayerInputs();
		Gdx.input.setInputProcessor(localPlayerInputs);
		
		//Network client launching
		socketClient = new GameClient(JOptionPane.showInputDialog("What is the IP adress of the server?"));
		socketClient.start();

		//Launching Identification packets
		Packet00Login loginPacket = new Packet00Login(localPlayerName);
		loginPacket.writeData(socketClient);

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
		Packet01Disconnect disconnectPacket =  new Packet01Disconnect(localPlayerName);
		disconnectPacket.writeData(socketClient);
		MapManager.map.dispose();
		renderer.dispose();
	}

}
