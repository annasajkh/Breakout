package com.github.annasajkh;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class Ball extends Rect
{
	
	Vector2 velocity;
	float speed = 10;

	public Ball(float x, float y)
	{
		super(x, y, 50, 50,Color.WHITE);
		velocity = new Vector2(1,0).rotate(MathUtils.random(225,315)).scl(speed);
	}

	@Override
	public void update()
	{
		updateBounds();
		
		if(x <= width * 0.5f || x > Gdx.graphics.getWidth() - width * 0.5f)
		{
			velocity.x = -velocity.x;
		}
		
		if(y > Gdx.graphics.getHeight() - height * 0.5f)
		{
			velocity.y = -velocity.y;
		}
		x += velocity.x;
		y += velocity.y;
	}

}
