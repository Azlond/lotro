package model;

import java.awt.Robot;

import javafx.scene.control.ListView;
import javafx.scene.input.KeyEvent;

@SuppressWarnings("serial")
public class KeyAction extends ActionObject{

	private int keyCode;
	private String keyName;
	private int pressDuration;

	@SuppressWarnings("deprecation")
	public KeyAction(KeyEvent event, int duration) {
		super(Action.key);
		this.setKeyName(event.getCode().getName());
		this.setKeyCode(event.getCode().impl_getCode());
		this.setPressDuration(duration);
	}

	protected KeyAction(int keyCode, String keyName, int duration){
		super(Action.key);
		this.setKeyName(keyName);
		this.setKeyCode(keyCode);
		this.setPressDuration(duration);
	}

	@Override
	public void perform(ListView<String> listView) throws InterruptedException{
		listView.getSelectionModel().clearAndSelect(this.getDisplayIndex());
		Robot robot = getRobot();
		robot.keyPress(this.getKeyCode());
		this.sleep(pressDuration);
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
		return "press key >" + this.getKeyName() + "< for " + this.getPressDuration() + " ms";
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String name) {
		this.keyName = name;
	}

	@Override
	public ActionObject getCopy(){
		return new KeyAction(this.getKeyCode(), this.getKeyName(), this.getPressDuration());
	}

	public int getPressDuration() {
		return pressDuration;
	}

	protected void setPressDuration(int pressDuration) {
		this.pressDuration = pressDuration;
	}
}
