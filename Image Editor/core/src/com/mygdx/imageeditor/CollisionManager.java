package com.mygdx.imageeditor;

import com.badlogic.gdx.math.Vector2;

public class CollisionManager {
	public static CollisionManager Instance;
	private Rec2D _recOne, _recTwo;
	public CollisionManager(Rec2D recOne, Rec2D recTwo) {
		_recOne = recOne;
		_recTwo = recTwo;
		Instance = this;
	}

	public Rec2D getCollision(Vector2 coordinates) {
		//Check to see if we collided with _recOne
		if(coordinates.x > _recOne.Position.x + _recOne.Scale.x) 
		{ return _recOne;}
		if (coordinates.y > _recOne.Position.y + _recOne.Scale.y) 
		{ return _recOne;}
		//Check to see if we collided with _recTwo
		if(coordinates.x > _recTwo.Position.x + _recTwo.Scale.x) 
		{ return _recTwo;}
		if(coordinates.y > _recTwo.Position.y + _recTwo.Scale.y) 
		{ return _recTwo;}
		
		return null;
	}
}
