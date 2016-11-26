package be.haraka.game2.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import be.haraka.game2.inputs.CameraManager;
import be.haraka.game2.map.MapManager;
import be.haraka.game2.map.MapUtil;
import be.haraka.game2.maths.IsometricMoovements;
import be.haraka.game2.maths.PlayerCollision;
import be.haraka.game2.screens.Play;

public class LocalPlayer{
	
	public static Vector2 pos;
	public static Vector2 velocity;
	public static Texture texture;
	public static float speed;
	public static Vector2 oldPos;
	public static PlayerCollision localPlayerCollision;
	public static boolean playerCenteredCamera;
	public static String username;
	
	public LocalPlayer(String name) {


		username = name;
		pos = new Vector2(0,0);
		speed = 2 * 60;
		texture = new Texture(Gdx.files.internal("Sources/entities/player3.png"));
		velocity = new Vector2();
		oldPos = new Vector2(pos.x,pos.y);
		localPlayerCollision = new PlayerCollision();
		playerCenteredCamera = false;
	}
	
	public void update(float delta)
	{
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
		
		
		
		//camera center
		CameraManager.cameraUpdate(delta);
		
	}
}
