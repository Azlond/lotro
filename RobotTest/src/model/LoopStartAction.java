package model;

import java.util.ArrayList;

import javafx.scene.control.ListView;

@SuppressWarnings("serial")
public class LoopStartAction extends ActionObject {

	private String name;
	private int iterations;
	private ArrayList<ActionObject> loopContent = new ArrayList<>();

	protected LoopStartAction(Action action) {
		super(action);
	}

	public LoopStartAction(String name, int iterations){
		super(Action.loopStart);
		this.setName(name);
		this.setIterations(iterations);
	}

	@Override
	public ActionObject getCopy() {
		LoopStartAction object = new LoopStartAction(this.getName(), this.getIterations());
		object.setLoopContent(this.getLoopContent());
		return object;
	}

	@Override
	public void perform(ListView<String> listView, boolean selectionOnly) throws InterruptedException {
		if(!selectionOnly){
			listView.getSelectionModel().clearAndSelect(this.getDisplayIndex());
		}

		if(selectionOnly && !listView.getSelectionModel().isSelected(this.getDisplayIndex())){
			return;
		}
		for(int i = 0; i < iterations; i++){
			for(ActionObject action : this.getLoopContent()){
				action.perform(listView, selectionOnly);
			}
		}
	}

	@Override
	public String getActionString() {
		return "Loop start >" + this.getName() + "< (" + this.getIterations() + " iterations)";
	}

	public String getName() {
		return name;
	}
	protected void setName(String name) {
		this.name = name;
	}

	public int getIterations() {
		return iterations;
	}
	protected void setIterations(int iterations) {
		this.iterations = iterations;
	}

	public ArrayList<ActionObject> getLoopContent() {
		return loopContent;
	}
	public void setLoopContent(ArrayList<ActionObject> loopContent) {
		this.loopContent = loopContent;
	}

}
