package model;

import java.awt.Robot;
import java.awt.event.InputEvent;

import javafx.scene.control.ListView;

@SuppressWarnings("serial")
public class DoubleClickAction extends ClickAction {

	public DoubleClickAction(double x, double y) {
		super(x, y);
		this.setAction(Action.doubleclick);
	}

	@Override
	public void perform(ListView<String> listView) throws InterruptedException{
		listView.getSelectionModel().clearAndSelect(this.getDisplayIndex());
		this.performDoubleClick();
	}

	@Override
	public String getActionString() {
		return "doubleclick at (" + this.getX() + "|" + this.getY() + ")";
	}

	@Override
	public ActionObject getCopy(){
		return super.getCopy();
	}

	public void performDoubleClick() throws InterruptedException{
		Robot robot = getRobot();
		robot.mouseMove(this.getX(), this.getY());
		this.sleep();
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		this.sleep();
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		this.sleep();
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		this.sleep();
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		this.sleep();
	}
}
