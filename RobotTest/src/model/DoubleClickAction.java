package model;

import javafx.scene.control.ListView;

@SuppressWarnings("serial")
public class DoubleClickAction extends ClickAction {

	public DoubleClickAction(double x, double y) {
		super(x, y);
		this.setAction(Action.doubleclick);
	}

	@Override
	public void perform(ListView<String> listView, boolean selectionOnly) throws InterruptedException{
		if(!selectionOnly){
			listView.getSelectionModel().clearAndSelect(this.getDisplayIndex());
		}

		if(selectionOnly && !listView.getSelectionModel().isSelected(this.getDisplayIndex())){
			return;
		}
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
