package com.github.annasajkh;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Rect
{
	float x;
	float y;
	float width;
	float height;
	protected float leftSide, rightSide, topSide, bottomSide;
	Color color;
	
	public Rect(float x, float y, float width, float height,Color color)
	{
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	public void updateBounds()
	{
		rightSide = x + width * 0.5f;
		leftSide = x - width * 0.5f;
		topSide = y + height * 0.5f;
		bottomSide = y - height * 0.5f;
	}
	
	public abstract void update();
	
	public void resolveCollision(Rect otherRect)
	{
		float collisionDeepX;
        float collisionDeepY;

        if (x > otherRect.x)
        {
            collisionDeepX = Math.abs(x - otherRect.x - width / 2 - otherRect.width / 2);
        }
        else
        {
            collisionDeepX = Math.abs(otherRect.x - x - width / 2 - otherRect.width / 2);
        }

        if (y > otherRect.y)
        {
            collisionDeepY = Math.abs(y - otherRect.y - height / 2 - otherRect.height / 2);
        }
        else
        {
            collisionDeepY = Math.abs(otherRect.y - y - height / 2 - otherRect.height / 2);
        }

        if (x < otherRect.x && collisionDeepX < collisionDeepY)
        {
            x -= collisionDeepX;
        }
        else if (x > otherRect.x && collisionDeepX < collisionDeepY)
        {
            x += collisionDeepX;
        }
        else if (y > otherRect.y && collisionDeepX > collisionDeepY)
        {
            y += collisionDeepY;
        }
        else if (y < otherRect.y && collisionDeepX > collisionDeepY)
        {
            y -= collisionDeepY;
        }
	}
	
	public boolean collisonDepthXGY(Rect otherRect)
	{
		
		float collisionDeepX;
        float collisionDeepY;

        if (x > otherRect.x)
        {
            collisionDeepX = Math.abs(x - otherRect.x - width / 2 - otherRect.width / 2);
        }
        else
        {
            collisionDeepX = Math.abs(otherRect.x - x - width / 2 - otherRect.width / 2);
        }

        if (y > otherRect.y)
        {
            collisionDeepY = Math.abs(y - otherRect.y - height / 2 - otherRect.height / 2);
        }
        else
        {
            collisionDeepY = Math.abs(otherRect.y - y - height / 2 - otherRect.height / 2);
        }
        
        return collisionDeepX > collisionDeepY;
            
	}
	
	public boolean intersect(Rect otherRect)
	{
		return (rightSide > otherRect.leftSide && 
				leftSide < otherRect.rightSide && 
				bottomSide < otherRect.topSide &&
                topSide > otherRect.bottomSide);
			
	}
	
	public void draw(ShapeRenderer shapeRenderer)
	{
		shapeRenderer.setColor(color);
		shapeRenderer.rect(x - width * 0.5f,y - height * 0.5f,width,height);
	}
	

}
