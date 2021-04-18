package com.github.annasajkh;

import com.badlogic.gdx.graphics.Color;

public class Player extends Rect
{

	public Player(float x, float y)
	{
		super(x, y, 200, 50, Color.YELLOW);
	}

	@Override
	public void update()
	{
		updateBounds();
		x = Thing.mousePos.x;
		
		if(intersect(Thing.ball))
		{			
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
