package model;

import javafx.scene.control.ListView;

@SuppressWarnings("serial")
public class LoopEndAction extends ActionObject {

	private String name;

	protected LoopEndAction(Action action) {
		super(action);
	}

	public LoopEndAction(String name){
		super(Action.loopEnd);
		this.setName(name);
	}

	@Override
	public ActionObject getCopy() {
		return new LoopEndAction(this.getName());
	}

	@Override
	public void perform(ListView<String> listView, boolean selectionOnly) throws InterruptedException {
		if(!selectionOnly){
			listView.getSelectionModel().clearAndSelect(this.getDisplayIndex());
		}
	}

	@Override
	public String getActionString() {
		return "Loop end >" + this.getName() + "<";
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
