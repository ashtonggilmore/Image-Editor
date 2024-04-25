package com.mygdx.imageeditor;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class InputManager implements InputProcessor{
	public static InputManager Instance;
	public Array<IClickable> Clickables = new Array<IClickable>();
	public Array<IHoverable> Hoverables = new Array<IHoverable>();
	public InputManager() {
		Instance = this;
		}
	
	private IHoverable _currentlyHovered;
	private IClickable _currentlyClicked;
	
	@Override
	public boolean keyDown(int keycode) {return false;}
	@Override
	public boolean keyUp(int keycode) {return false;}
	@Override
	public boolean keyTyped(char character) {return false;}
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		System.out.println("test");
		IClickable click = CollisionManager.Instance.getClicked(
			new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
		_currentlyClicked = click;
		if (click != null) {
			click.onClickDown(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));};
		return true;}
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (_currentlyClicked != null) {
			_currentlyClicked.onClickUp(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
		}
		System.out.println("clicking up");
		return true;}
	@Override
	public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {return false;}
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		mouseMoved(screenX, screenY);
		if(_currentlyClicked != null)
			 _currentlyClicked.onClickDragged(new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
		return true;}
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		IHoverable hover = CollisionManager.Instance.getHovered(
				new Vector2(screenX, ImageEditor.Instance.ScreenSize.y - screenY));
			if (hover != null) {hover.onHovered();}		
			if (_currentlyHovered != null && _currentlyHovered != hover) {
				_currentlyHovered.onHoverExit();
			}
			_currentlyHovered = (IHoverable) hover;
			return true;
	}
	@Override
	public boolean scrolled(float amountX, float amountY) {return false;}
}
