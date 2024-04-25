package com.mygdx.imageeditor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class ImageEditor extends ApplicationAdapter {
	SpriteBatch batch;
	public Vector2 ScreenSize;
	public static ImageEditor Instance;
	public Array<Rec2D> Rectangles = new Array<Rec2D>();
	private EditWindow _editWindow;
	Button button1;

	@Override
	public void create () {
		Instance = this;
		new ImageInputOutput();
		Pixmap editMap = ImageInputOutput.Instance.loadImage("blackbuck.bmp");
		InputManager inputManager = new InputManager();
		Gdx.input.setInputProcessor(inputManager);
		batch = new SpriteBatch();
		ScreenSize = new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		//temporary button
		button1 = new Button(new Vector2 (50,50), new Vector2(5,5), Color.YELLOW);
		
		Vector2 editWindowSize = new Vector2(500, ScreenSize.y - 50);
			_editWindow = new EditWindow(editWindowSize, new Vector2(ScreenSize.x - editWindowSize.x, 0), Color.GRAY);
			
			
		CollisionManager collisionManager = new CollisionManager();
		_editWindow.DoodleTexture = new Texture(editMap);
		}

	@Override
	public void render () {
		ScreenUtils.clear(0f, 0f, 0f, 1);
		batch.begin();
		Rec2D rec;
		for(int i = 0; i < Rectangles.size; i++) {
			rec = this.Rectangles.get(i);
			batch.draw(rec.RecTexture, rec.Position.x, rec.Position.y, rec.Scale.x, rec.Scale.y);
		}
		//temporary button
		batch.draw(button1.RecTexture,  button1.Position.x, button1.Position.y);
		
		batch.draw(_editWindow.DoodleTexture, _editWindow.Position.x,
				_editWindow.Position.y, _editWindow.Scale.x, _editWindow.Scale.y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
