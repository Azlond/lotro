package model;

import java.awt.Robot;

import javafx.scene.input.KeyEvent;

@SuppressWarnings("serial")
public class KeyAction extends ActionObject{

	private int keyCode;
	private String keyName;

	@SuppressWarnings("deprecation")
	public KeyAction(KeyEvent event) {
		super(Action.key);
		this.setKeyName(event.getCode().getName());
		this.setKeyCode(event.getCode().impl_getCode());
	}

	protected KeyAction(int keyCode, String keyName){
		super(Action.key);
		this.setKeyName(keyName);
		this.setKeyCode(keyCode);
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

	@Override
	public ActionObject getCopy(){
		return new KeyAction(this.getKeyCode(), this.getKeyName());
	}
}
