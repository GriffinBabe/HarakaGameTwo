package be.haraka.game2.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.IsometricTiledMapRenderer;

public class MapManager 
{
	public static TiledMap map;
	public IsometricTiledMapRenderer renderer;
	
	
	public MapManager() 
	{
		map = new TmxMapLoader().load(Gdx.files.internal("Sources/maps/map6.tmx").file().getAbsolutePath());
		renderer = new IsometricTiledMapRenderer(map);
	}
	
	public void render(OrthographicCamera camera)
	{
		renderer.render();
		renderer.setView(camera);
	}
	
	public static float getMapWidth()
	{
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(0);
		return layer.getWidth() * layer.getTileWidth();
	}
	
	public static float getMapHeight()
	{
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(0);
		return layer.getHeight() * layer.getTileHeight();
	}
	
	public static float getTileWidth()
	{
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(0);
		return layer.getTileWidth();
	}
	
	public static float getTileHeight()
	{
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(0);
		return layer.getTileHeight();
	}
	
	public static int getMapXTiles()
	{
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(0);
		return layer.getWidth();
	}
	
	public static int getMapYTiles()
	{
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(0);
		return layer.getHeight();
	}

}
