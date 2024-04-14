package com.mygdx.imageeditor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

public class Button extends Rec2D{
	private static int _buttonCount;
	private int _buttonNumber;
	
	public Button(Vector2 scale, Vector2 position, Color recColor) {
		super(scale, position, recColor);
		InputManager.Instance.Buttons.add(this);
		_buttonCount +=1;
		_buttonNumber = _buttonCount;
		
	}
	
	public static void onPressed(int CurrentButton) {
		System.out.println("You've pressed button " + CurrentButton);
	}

}