package model;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.Serializable;

import util.Log;
import data.Keys;


public abstract class ActionObject implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public enum Action{
		doubleclick, click, key, wait, none; //use none only for error return values
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
		this.setAction(action);
	}

	public abstract ActionObject getCopy();
	public abstract void perform() throws InterruptedException;
	/**
	 * @return a description like "click at (0|0)"
	 */
	public abstract String getActionString();

	public void sleep(int ms) throws InterruptedException{
		Thread.sleep(ms);
	}

	public void sleep() throws InterruptedException{
		Thread.sleep(WAIT_BETWEEN_EVENTS);
	}

	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public String getActionAsString(){
		return Keys.getStringForAction(this.getAction());
	}
}
