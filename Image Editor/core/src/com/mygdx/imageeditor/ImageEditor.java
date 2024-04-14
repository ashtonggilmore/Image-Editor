package com.mygdx.imageeditor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;

public class ImageEditor extends ApplicationAdapter {
	SpriteBatch batch;
	Button button1;
	Button button2;
	Button button3;
	Button button4;
	Button button5;
	public Vector2 ScreenSize;
	public static ImageEditor Instance;

	@Override
	public void create () {
		Instance = this;
		batch = new SpriteBatch();
		ScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		InputManager inputManager = new InputManager();
		Gdx.input.setInputProcessor(inputManager);
		Vector2 rectangleScale = new Vector2(100,100);
		button1 = new Button(
			rectangleScale,
			new Vector2(ScreenSize.x / 2f - rectangleScale.x + 200, ScreenSize.y / 2f - rectangleScale.y + 200),
			Color.BLUE);
		button2 = new Button(
			rectangleScale,
			new Vector2(ScreenSize.x / 2f - rectangleScale.x / 2, ScreenSize.y / 2f - rectangleScale.y / 2),
			Color.WHITE);
		button3 = new Button(
			rectangleScale,
			new Vector2(ScreenSize.x / 2f + rectangleScale.x - 300, ScreenSize.y / 2f + rectangleScale.y - 300),
			Color.ORANGE);
		button4 = new Button(
			rectangleScale,
			new Vector2(ScreenSize.x / 2f - rectangleScale.x - 100, ScreenSize.y / 2f - rectangleScale.y + 200),
			Color.RED);
		button5 = new Button(
			rectangleScale,
			new Vector2(ScreenSize.x / 2f - rectangleScale.x + 200, ScreenSize.y / 2f - rectangleScale.y - 100),
			Color.GREEN);
		CollisionManager collisionManager = new CollisionManager();
		}

	@Override
	public void render () {
		ScreenUtils.clear(0f, 0f, 0f, 1);
		batch.begin();
		batch.draw(button1.RecTexture, button1.Position.x, button1.Position.y);
		batch.draw(button2.RecTexture, button2.Position.x, button2.Position.y);
		batch.draw(button3.RecTexture, button3.Position.x, button3.Position.y);
		batch.draw(button4.RecTexture, button4.Position.x, button4.Position.y);
		batch.draw(button5.RecTexture, button5.Position.x, button5.Position.y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
