package model;

import javafx.scene.control.ListView;

@SuppressWarnings("serial")
public class WaitAction extends ActionObject {

	private int duration;

	public WaitAction(int ms) {
		super(Action.wait);
		this.setDuration(ms);
	}

	@Override
	public void perform(ListView<String> listView, boolean selectionOnly) throws InterruptedException{
		if(!selectionOnly){
			listView.getSelectionModel().clearAndSelect(this.getDisplayIndex());
		}

		if(selectionOnly && !listView.getSelectionModel().isSelected(this.getDisplayIndex())){
			return;
		}
		this.sleep(this.getDuration());
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	@Override
	public String getActionString() {
		return "wait " + this.getDuration() + " ms";
	}

	@Override
	public ActionObject getCopy(){
		return new WaitAction(this.getDuration());
	}
}
