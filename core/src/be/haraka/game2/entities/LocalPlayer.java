package be.haraka.game2.entities;

import javax.swing.JOptionPane;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import be.haraka.game2.map.MapUtil;
import be.haraka.game2.maths.PlayerCollision;
import be.haraka.game2.net.GameClient;
import be.haraka.game2.net.packets.Packet00Login;
import be.haraka.game2.net.packets.Packet01Disconnect;

public class LocalPlayer implements InputProcessor {
	
	//Networking elements
	private GameClient socketClient;
	public boolean isPlayerConnected;
	
	//Player stats
	public Vector2 pos;
	public Vector2 velocity;
	public Texture texture;
	public float speed;
	public Vector2 oldPos;
	public PlayerCollision localPlayerCollision;
	public boolean playerCenteredCamera;
	public String username;
	
	public LocalPlayer(String name) {

		//Stats
		username = name;
		pos = new Vector2(0,0);
		speed = 2 * 60;
		texture = new Texture(Gdx.files.internal("Sources/entities/player3.png"));
		velocity = new Vector2();
		oldPos = new Vector2(pos.x,pos.y);
		localPlayerCollision = new PlayerCollision();
		playerCenteredCamera = false;
		
		//Network client launching
		socketClient = new GameClient(JOptionPane.showInputDialog("What is the IP adress of the server?"));
		socketClient.start();
		
		login();

	}
	
	//Player Loop
	public void update(float delta) {
		oldPos.set(pos);
		pos.x = pos.x + velocity.x * delta;
		pos.y = pos.y + velocity.y * delta;
		//System.out.println("LocalPlayer iso pos "+MapUtil.gameToIso(LocalPlayer.pos).x+" "+MapUtil.gameToIso(LocalPlayer.pos).y);
		//System.out.println("LocalPlayer pos "+pos.x+ " "+pos.y);
		

		
		
		//LocalPlayer Collision
		/*
		if(MapUtil.gameToIso(LocalPlayer.pos).x <= MapManager.getMapXTiles() && MapUtil.gameToIso(LocalPlayer.pos).y <= MapManager.getMapYTiles())
		{
			
			if (PlayerCollision.playerCollisionCheck())
			{
				System.out.println("collision");
				pos.set(oldPos.x,oldPos.y);
				velocity.set(0,0);
			}
		}*/
		
	}

	public boolean keyDown(int keycode) {
		switch(keycode){
		case Keys.Z:
			velocity.y = speed;
			break;
		case Keys.Q:
			velocity.x = - speed;
			break;
		case Keys.D:
			velocity.x = speed;
			break;
		case Keys.S:
			velocity.y = - speed;
			break;
		case Keys.SPACE:
			//Toggle boolean trick
			playerCenteredCamera = !playerCenteredCamera;
			break;
		case Keys.ESCAPE:
			//Disconnect
			disconnect();
			break;
		}

		return true;
	}

	public boolean keyUp(int keycode) {
		switch(keycode){
		case Keys.Z:
			velocity.y = 0;
			break;
		case Keys.Q:
			velocity.x = 0;
			break;
		case Keys.D:
			velocity.x = 0;
			break;
		case Keys.S:
			velocity.y = 0;
			break;
		}
		return true;
	}


	public boolean keyTyped(char character) {
		return false;
	}


	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (button == Buttons.LEFT){
        	pos.x = 0;
        	pos.y = 0;
        	System.out.println(MapUtil.gameToIso(pos).x + " " + MapUtil.gameToIso(pos).y);
        }
        
        if(button == Buttons.RIGHT){
        	pos.x = screenX-texture.getWidth()/2;
        	pos.y = Gdx.graphics.getHeight()/2-screenY-texture.getHeight()/4;
        	System.out.println(MapUtil.gameToIso(pos).x + " " + MapUtil.gameToIso(pos).y);
        	System.out.println(texture.getHeight());
        	System.out.println(texture.getWidth());
            
            
        }
		return false;
	}


	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}


	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}


	public boolean mouseMoved(int screenX, int screenY){
		return false;
	}


	public boolean scrolled(int amount) {
		return false;
	}
	
	public void login() {
		//Launching Identification packets
		Packet00Login loginPacket = new Packet00Login(username);
		loginPacket.writeData(socketClient);
		isPlayerConnected = true;
	}
	

	public void disconnect() {
		if(JOptionPane.showConfirmDialog(null, "Sure u wanna leave kiddo?") == JOptionPane.YES_OPTION){
			isPlayerConnected = false;
			Packet01Disconnect disconnectPacket = new Packet01Disconnect(username);
			disconnectPacket.writeData(socketClient);
		}
		
	}
}
