package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class EditWindow extends Rec2D implements IClickable {
	private static EditWindow Instance;
	public Texture DoodleTexture;
	public Pixmap _doodleMap;
	private Vector2 _previousPaintPosition;

	public EditWindow(Vector2 scale, Vector2 position, Texture imageTex) {
		super(scale, position, Color.GRAY);
		if(Instance == null) {
			Instance = new EditWindow(position, position, imageTex);
        }
		RecTexture = imageTex;

		InputManager.Instance.Clickables.add(this);
		_doodleMap = new Pixmap((int) scale.x, (int) scale.y, Format.RGBA8888);
		_doodleMap.setColor(Color.ORANGE);
		DoodleTexture = new Texture(_doodleMap);
	}

	public void onClickDown(Vector2 clickPosition)  {
		if(_previousPaintPosition == null)
			_previousPaintPosition = new Vector2(clickPosition.x - Position.x,Scale.y - clickPosition.y);
		paintAtPosition(clickPosition);
	}
	@Override
	public void onClickUp(Vector2 clickPosition) {
		_previousPaintPosition = null;
		
	}
	private void paintAtPosition(Vector2 worldPosition) {
		Vector2 paintPosition = new Vector2(worldPosition.x - Position.x,Scale.y - worldPosition.y);
		int startX = (int) _previousPaintPosition.x;
		int startY = (int)_previousPaintPosition.y;
		int endX = (int) paintPosition.x;
		int endY = (int) paintPosition.y;
		_doodleMap.drawLine(startX, startY, endX, endY);
		_doodleMap.drawLine(startX + 1, startY, endX + 1, endY);
		_doodleMap.drawLine(startX - 1, startY, endX - 1, endY);
		_previousPaintPosition = paintPosition;
		DoodleTexture = new Texture(_doodleMap);
	}	
		
	@Override
	public void onClickDragged(Vector2 clickPosition){
		paintAtPosition(clickPosition);
		}
}