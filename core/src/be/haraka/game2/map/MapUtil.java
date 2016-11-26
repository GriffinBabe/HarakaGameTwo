package be.haraka.game2.map;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class MapUtil
{
	
		private static Vector2 mapTrans = new Vector2();
		private static Vector2 isoTrans = new Vector2();

		public static Vector2 gameToIso(Vector2 game)
		{

			mapTrans.x =-( game.x + MapManager.getMapWidth()/2);
			mapTrans.y = MapManager.getMapHeight()/2 - game.y;
			
			isoTrans.x =(int) ( ( mapTrans.x / (MapManager.getTileWidth()) ) + ( mapTrans.y / (MapManager.getTileHeight()) ) );
			isoTrans.y =(int) ( (mapTrans.y / (MapManager.getTileHeight()) ) - ( mapTrans.x / (MapManager.getTileWidth()) ) );
			
			return isoTrans;
		}
}
