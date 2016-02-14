package model;

import java.awt.Robot;

import javafx.scene.input.KeyEvent;

public class KeyAction extends ActionObject{
	
	private int keyCode;
	private String keyCharacter;

	public KeyAction(KeyEvent event) {
		super(Action.key);
		this.setKeyCharacter(event.getCharacter());
		//TODO get keyCode from event (maybe deprecated impl_keyCode?)
	}

	@Override
	public void perform() {
		Robot robot = getRobot();
		robot.keyPress(this.getKeyCode());
		this.sleep();
		robot.keyRelease(this.getKeyCode());
		this.sleep();
	}

	public int getKeyCode() {
		return keyCode;
	}
	public void setKeyCode(int keyCode) {
		this.keyCode = keyCode;
	}
	
	@Override
	public String getActionString() {
		return "press key >" + this.getKeyCharacter() + "<";
	}

	public String getKeyCharacter() {
		return keyCharacter;
	}

	public void setKeyCharacter(String keyCharacter) {
		this.keyCharacter = keyCharacter;
	}
}
