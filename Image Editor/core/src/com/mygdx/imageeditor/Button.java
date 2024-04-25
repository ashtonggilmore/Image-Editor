package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Button extends Rec2D implements IClickable, IHoverable{
	private Color _startColor;
	private Color _hoveredColor;
	public enum ButtonState {Clicked, Hovered, None};
	private ButtonState _state;
	
	public Button(Vector2 scale, Vector2 position, Color recColor) {
		super(scale, position, recColor);
		Rec2D Instance = this;
		_startColor = recColor;
		_hoveredColor = new Color(_startColor.r / 2f, _startColor.g / 2f, _startColor.b / 2f, 1);
		_state = ButtonState.None;
		InputManager.Instance.Hoverables.add(this);
		InputManager.Instance.Clickables.add(this);
	}
	
	public void onClickDown(Vector2 clickPosition) {
//		Button Instance = this;
		while (_state != ButtonState.Clicked) { 
		if (_state == ButtonState.Clicked) {
			return;
		}
		
		_recColor = new Color(_startColor.r / 4f, _startColor.g / 4f, _startColor.b / 4f, 1);
		_state = ButtonState.Clicked;
		System.out.println("draw?");
		generateTexture();
		}
	}
	
	public void onClickUp(Vector2 clickPosition) {
		Button Instance = this;
		_recColor = _hoveredColor;
		_state = ButtonState.Hovered;
		generateTexture();
	}
	
	@Override
	public void onClickDragged(Vector2 clickPosition) {
	// TODO Auto-generated method stub
	}

	
	public void onHovered() {
		Button Instance = this;
		while (_state != ButtonState.Hovered) {
		if (_state == ButtonState.Clicked) {
			return;
		}
		_recColor = _hoveredColor;
		_state = ButtonState.Hovered;
		generateTexture();
		}
	}
	
	public void onHoverExit() {
		Button Instance = this;
		_recColor = _startColor;
		_state = ButtonState.None;
		generateTexture();
	}
	
}
