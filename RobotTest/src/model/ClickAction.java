package model;

import java.awt.Robot;
import java.awt.event.InputEvent;

import javafx.scene.control.ListView;

@SuppressWarnings("serial")
public class ClickAction extends ActionObject {

	private int x, y;

	public ClickAction(double x, double y) {
		super(Action.click);
		this.setX((int)x);
		this.setY((int)y);
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}

	@Override
	public void perform(ListView<String> listView) throws InterruptedException {
		listView.getSelectionModel().clearAndSelect(this.getDisplayIndex());
		this.performClick();
	}

	protected void performClick() throws InterruptedException {
		Robot robot = getRobot();
		robot.mouseMove(this.getX(), this.getY());
		this.sleep(1000);
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		this.sleep();
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		this.sleep();
	}

	@Override
	public String getActionString() {
		return "click at (" + this.getX() + "|" + this.getY() + ")";
	}

	@Override
	public ActionObject getCopy(){
		return new ClickAction(this.getX(), this.getY());
	}

}
