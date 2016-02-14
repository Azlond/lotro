package model;

import java.awt.AWTException;
import java.awt.Robot;

import util.Log;


public abstract class ActionObject {
	enum Action{
		doubleclick, click, key, wait;
	}
	
	public static final int WAIT_BETWEEN_EVENTS = 30;
	private static Robot robot;
	protected static Robot getRobot(){
		if(robot == null){
			try {
				robot = new Robot();
			} catch(AWTException e) {
				Log.log(e);
			}
		}
		return robot;
	}
	
	private Action action;
	
	protected ActionObject(Action action){
	}
	
	public abstract void perform();
	public abstract String getActionString();
	
	public void sleep(int ms){
		try {
			Thread.sleep(ms);
		} catch(InterruptedException e) {
			Log.log(e);
		}
	}
	
	public void sleep(){
		try {
			Thread.sleep(WAIT_BETWEEN_EVENTS);
		} catch(InterruptedException e) {
			Log.log(e);
		}
	}

	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
}
