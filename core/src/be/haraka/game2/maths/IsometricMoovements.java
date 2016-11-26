package be.haraka.game2.maths;

import com.badlogic.gdx.math.Vector2;

public class IsometricMoovements 

{
	
	public static Vector2 gamevelToIsovel(Vector2 velocity)
	{
		velocity.x = (float) (velocity.x * Math.cos(105) + velocity.y * Math.cos(225));
		velocity.y = (float) (velocity.x * Math.sin(105) + velocity.y * Math.sin(225));
		return velocity;
	}
}
