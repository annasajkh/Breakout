package com.github.annasajkh;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector3;

public class Thing extends ApplicationAdapter
{
	
	ShapeRenderer shapeRenderer;
	OrthographicCamera camera;
	public static Vector3 mousePos;
	public static Ball ball;
	boolean end = false;
	BitmapFont font;
	SpriteBatch spriteBatch;
	public static Player player;
	List<Brick> bricks;
	
	@Override
	public void create() 
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		shapeRenderer = new ShapeRenderer();
		font = new BitmapFont();
		spriteBatch = new SpriteBatch();
		
		player = new Player(Gdx.graphics.getWidth() / 2,50);
		bricks = new ArrayList<>();
		ball = new Ball(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2);
		camera = new OrthographicCamera(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
		
		camera.position.x = Gdx.graphics.getWidth() / 2;
		camera.position.y = Gdx.graphics.getHeight() / 2;
		camera.update();
		
		for (int i = 0; i < 5; i++)
		{
			for (int j = 0; j < 10; j++)
			{
				bricks.add(new Brick(j * 100 + 50,575 - i * 50));
			}
		}
	}
	
	public void update()
	{
		mousePos = camera.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(),0));
		player.update();
		ball.update();
		
		for(int i = bricks.size() - 1;i >= 0; i--)
		{
			Brick brick = bricks.get(i);
					
			brick.update();
				
			if(brick.remove)
			{
				bricks.remove(i);
			}
			
		}
		
		if(bricks.isEmpty())
		{
			end = true;
			return;
		}
		
		if(ball.y < -ball.height)
		{
			create();
		}
		camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);
		
	}

	@Override
	public void render()
	{

		if(Gdx.input.isKeyJustPressed(Keys.R))
		{
			create();
		}
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(!end)
		{			
			update();
			if(end)
			{
				return;
			}
			shapeRenderer.begin(ShapeType.Filled);
			for(Brick brick : bricks)
			{
				if(brick != null)
				{				
					brick.draw(shapeRenderer);
				}
			}
			ball.draw(shapeRenderer);
			player.draw(shapeRenderer);
			shapeRenderer.end();
		}
		else
		{
			spriteBatch.begin();
			font.draw(spriteBatch,"You Win",Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2 - 50);
			spriteBatch.end();
		}
	}
	
	@Override
	public void dispose()
	{
		shapeRenderer.dispose();
		spriteBatch.dispose();
	}
}
