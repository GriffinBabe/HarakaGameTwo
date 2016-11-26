package be.haraka.game2.maths;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import be.haraka.game2.entities.LocalPlayer;
import be.haraka.game2.map.MapManager;
import be.haraka.game2.map.MapUtil;

public class PlayerCollision 
{
	
	public static TiledMapTileLayer collisionLayer;
	
	public PlayerCollision()
	{
		collisionLayer = (TiledMapTileLayer) MapManager.map.getLayers().get("collisionlayer");
	}
	
	public static boolean playerCollisionCheck(LocalPlayer localPlayer)
	{
		//Checks is there is the "blocked" the blocked statement in player Tile position, there's a bug I can't find that mooves the collision layer 2 tiled before in y axis, so I remoove 2
		if ( collisionLayer.getCell((int) MapUtil.gameToIso(localPlayer.pos).x, (int) MapUtil.gameToIso(localPlayer.pos).y).getTile().getProperties().containsKey("blocked") )
		{
			return true;
		}
		
		else
			return false;
		
		
	}
	
	public static String getTileProperties(Vector2 isoPos)
	{
		return collisionLayer.getCell((int) isoPos.x,(int) isoPos.y).getTile().getProperties().toString();
	}
}
