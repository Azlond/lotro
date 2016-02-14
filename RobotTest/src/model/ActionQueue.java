package model;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class ActionQueue {
	private ObservableList<String> displayList = FXCollections.observableArrayList(new ArrayList<String>());
	private ArrayList<ActionObject> actionList = new ArrayList<>();
	
	public ActionQueue(ListView<String> listView){
		Platform.runLater(() -> {
			listView.setItems(displayList);
		});
	}
	
	public void addItem(ActionObject object){
		this.getDisplayList().add(object.getActionString());
		this.getActionList().add(object);
	}
	
	public void addItem(ActionObject object, int index){
		this.getDisplayList().add(index, object.getActionString());
		this.getActionList().add(index, object);
	}
	
	public void removeItem(int index){
		this.getDisplayList().remove(index);
		this.getActionList().remove(index);
	}
	
	public void moveItemUp(int index){
		if(index == 0){
			return;
		}
		ActionObject actionItem = this.getActionList().get(index);
		this.removeItem(index);
		this.addItem(actionItem, index - 1);
	}

	protected ObservableList<String> getDisplayList() {
		return displayList;
	}
	protected void setDisplayList(ObservableList<String> displayList) {
		this.displayList = displayList;
	}

	protected ArrayList<ActionObject> getActionList() {
		return actionList;
	}
	protected void setActionList(ArrayList<ActionObject> actionList) {
		this.actionList = actionList;
	}
	
}
