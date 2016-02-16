package model;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;

public class ActionQueue {
	private ObservableList<String> displayList = FXCollections.observableArrayList(new ArrayList<String>());
	private ArrayList<ActionObject> actionList = new ArrayList<>();
	private ListView<String> listView;
	private Thread runThread, runForeverThread;
	
	public ActionQueue(ListView<String> listView){
		this.setListView(listView);
		this.setRunThread(new ActionThread());
		this.setRunForeverThread(new ActionLoopThread());
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
	
	public void run(){
		this.stopThreads();
		this.getRunThread().start();
	}
	
	public void runForever(){
		this.stopThreads();
		this.getRunForeverThread().start();
	}
	
	public void stop(){
		this.stopThreads();
	}
	
	protected void runSequence(){
		int index = 0;
		for(ActionObject action : this.getActionList()){
			this.getListView().getSelectionModel().clearAndSelect(index);
			action.perform();
			index++;
		}
		this.getListView().getSelectionModel().clearSelection();
	}
	
	protected void stopThreads(){
		if(this.getRunThread().isAlive()){
			this.getRunThread().interrupt();
		}
		if(this.getRunForeverThread().isAlive()){
			this.getRunForeverThread().interrupt();
		}
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

	protected ListView<String> getListView() {
		return listView;
	}
	protected void setListView(ListView<String> listView) {
		this.listView = listView;
	}

	public Thread getRunThread() {
		return runThread;
	}
	public void setRunThread(Thread runThread) {
		this.runThread = runThread;
	}
	
	public Thread getRunForeverThread() {
		return runForeverThread;
	}
	public void setRunForeverThread(Thread runForeverThread) {
		this.runForeverThread = runForeverThread;
	}

	private class ActionThread extends Thread{
		@Override
		public void run(){
			synchronized(ActionQueue.this.getListView()){
				ActionQueue.this.runSequence();
				ActionQueue.this.getListView().notifyAll();
			}
		}
	}
	private class ActionLoopThread extends Thread{
		@Override
		public void run(){
			synchronized(ActionQueue.this.getListView()){
				while(true){
					ActionQueue.this.runSequence();
				}
			}
		}
	}
	
}
