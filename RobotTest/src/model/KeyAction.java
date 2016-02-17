package model;

import java.awt.Robot;

import javafx.scene.input.KeyEvent;

public class KeyAction extends ActionObject{
	
	private int keyCode;
	private String keyName;

	public KeyAction(KeyEvent event) {
		super(Action.key);
		this.setKeyName(event.getCode().getName());
		this.setKeyCode(event.getCode().impl_getCode());
	}

	@Override
	public void perform() throws InterruptedException{
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
		return "press key >" + this.getKeyName() + "<";
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String name) {
		this.keyName = name;
	}
}
