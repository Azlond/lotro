package model;

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
		this.performClick();
		this.performClick();
	}

	@Override
	public String getActionString() {
		return "doubleclick at (" + this.getX() + "|" + this.getY() + ")";
	}

	@Override
	public ActionObject getCopy(){
		return super.getCopy();
	}
}
