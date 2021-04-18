package com.github.annasajkh;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;

public class Brick extends Rect
{
	
	public boolean remove = false;

	public Brick(float x, float y)
	{
		super(x,y,100, 50, new Color[] {Color.RED,Color.GREEN,Color.BLUE}[MathUtils.random(2)]);
	}

	@Override
	public void update()
	{
		updateBounds();
		if(intersect(Thing.ball))
		{
			remove = true;
			
			if(collisonDepthXGY(Thing.ball))
			{

				Thing.ball.velocity.y = -Thing.ball.velocity.y;
			}
			else
			{
				Thing.ball.velocity.x = -Thing.ball.velocity.x;
			}
			
			Thing.ball.resolveCollision(this);
		}
		
	}
	
	

}
