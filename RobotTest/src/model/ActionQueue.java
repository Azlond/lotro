package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import util.Log;
import util.Util;

public class ActionQueue implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		if(object != null && object.getActionString() != null){
			this.getDisplayList().add(object.getActionString());
			this.getActionList().add(object);
		}
	}

	protected void addItem(ActionObject object, int index){
		if(object != null && object.getActionString() != null){
			this.getDisplayList().add(index, object.getActionString());
			this.getActionList().add(index, object);
		}
	}

	/**
	 * removes the selected Items<br/>
	 * triggers only one event
	 */
	public void removeSelectedItems(){
		int[] selectedIndices = Util.toIntArray(this.getListView().getSelectionModel().getSelectedIndices());
		Arrays.sort(selectedIndices);
		ObservableList<String> displayCopy = FXCollections.observableArrayList(this.getDisplayList());

		for(int index = selectedIndices.length - 1; index >= 0; index--){
			displayCopy.remove(selectedIndices[index]);
			this.getActionList().remove(selectedIndices[index]);
		}
		this.setDisplayList(displayCopy);
		this.getListView().setItems(displayCopy);
		this.getListView().getSelectionModel().clearSelection();
	}

	/**
	 * creates a copy of every Action currently selected and inserts it at the end of the list
	 */
	public void duplicateSelectedItems(){
		int[] selectedIndices = Util.toIntArray(this.getListView().getSelectionModel().getSelectedIndices());
		Arrays.sort(selectedIndices);
		List<ActionObject> sortedCopies = new ArrayList<ActionObject>();
		List<String> sortedStringCopies = new ArrayList<String>();
		for(int i : selectedIndices){
			sortedCopies.add(this.getActionList().get(i).getCopy());
			sortedStringCopies.add(new String(this.getDisplayList().get(i)));
		}

		this.getActionList().addAll(sortedCopies);
		this.getDisplayList().addAll(sortedStringCopies);
	}

	public void edit(){
		/* TODO think about how to implement edit
		 * thoughts so far:
		 * - ending the edit mode can neither be by key combo nor by mouseclick (because of the event filters)
		 *  -> a combination? key when setting clicks, click when setting keys
		 * - lock the action selection and disable all buttons until edit mode is lifted
		 */
	}

	/**
	 * moves the selected items one space<br/>
	 * <br/>
	 * triggers only one event
	 * @param direction +1/-1
	 * @param indices sorted Array of indices
	 * @return
	 */
	protected int[] shiftItems(int[] indices, int direction){
		ObservableList<String> listCopy = FXCollections.observableArrayList(this.getDisplayList());
		ArrayList<ActionObject> objectList = this.getActionList();
		for(int index : indices){
			{
				String item = listCopy.get(index);
				listCopy.remove(index);
				listCopy.add(index - direction, item);
			}
			{
				ActionObject item = objectList.get(index);
				objectList.remove(index);
				objectList.add(index - direction, item);
			}
		}
		this.setDisplayList(listCopy);
		this.getListView().setItems(listCopy);
		for(int i = 0; i < indices.length; i++){
			indices[i] -= direction;
		}
		return indices;
	}

	protected int[] checkBoundariesAndMove(int[] indices, int direction){
		Arrays.sort(indices);
		if(direction == -1){

			for(int i : indices){
				//check if bottom element is selected
				if(i == this.getActionList().size() - 1){
					return indices;
				}
			}
			//revert sort
			int[] sorted = new int[indices.length];
			for(int i = 0; i < indices.length; i++){
				sorted[i] = indices[indices.length - i - 1];
			}
			indices = sorted;

		} else{

			for(int i : indices){
				//check if top element is selected
				if(i == 0){
					return indices;
				}
			}
		}
		return this.shiftItems(indices, direction);
	}

	/**
	 * @param direction +1/-1
	 */
	public void moveItems(int direction){
		List<Integer> indices = new ArrayList<Integer>(this.getListView().getSelectionModel().getSelectedIndices());
		if(indices.isEmpty() || Math.abs(direction) != 1){
			return;
		}
		int[] newSelection = this.checkBoundariesAndMove(Util.toIntArray(indices), direction);

		if(indices.size() > 1){
			this.getListView().getSelectionModel().clearSelection();
			this.getListView().getSelectionModel().selectIndices(newSelection[0], newSelection);
		} else{
			this.getListView().getSelectionModel().clearAndSelect(newSelection[0]);
		}
	}

	public void run(){
		this.stopThreads();
		this.startRunThread();
	}

	public void runForever(){
		this.stopThreads();
		this.startRunForeverThread();
	}

	public void stop(){
		this.stopThreads();
	}

	protected void runSequence() throws InterruptedException{
		int index = 0;
		for(ActionObject action : this.getActionList()){
			this.getListView().getSelectionModel().clearAndSelect(index);
			action.perform();
			index++;
		}
		this.getListView().getSelectionModel().clearSelection();
	}

	protected void stopThreads(){
		Log.log("attempting to stop Threads", Log.Level.DEBUG);
		try {
			if(this.getRunThread().isAlive()){
				this.getRunThread().interrupt();
				this.getRunThread().join();
			}
		} catch (InterruptedException e) {
			Log.log(e, Log.Level.TRACE);
		}
		try {
			if(this.getRunForeverThread().isAlive()){
				this.getRunForeverThread().interrupt();
				this.getRunForeverThread().join();
			}
		} catch (InterruptedException e) {
			Log.log(e, Log.Level.TRACE);
		}
	}


	public ObservableList<String> getDisplayList() {
		return displayList;
	}
	protected void setDisplayList(ObservableList<String> displayList) {
		this.displayList = displayList;
	}

	public ArrayList<ActionObject> getActionList() {
		return actionList;
	}
	public void setActionList(ArrayList<ActionObject> actionList) {
		this.actionList = actionList;
	}

	protected ListView<String> getListView() {
		return listView;
	}
	protected void setListView(ListView<String> listView) {
		this.listView = listView;
	}

	protected void startRunThread(){
		if(!this.getRunThread().isAlive()){
			this.setRunThread(new ActionThread());
			this.getRunThread().start();
		}
	}
	protected Thread getRunThread() {
		return runThread;
	}
	protected void setRunThread(Thread runThread) {
		runThread.setDaemon(true);
		this.runThread = runThread;
	}

	protected void startRunForeverThread(){
		this.setRunForeverThread(new ActionLoopThread());
		this.getRunForeverThread().start();
		Log.log("new runForeverThread started", Log.Level.DEBUG);
	}
	protected Thread getRunForeverThread() {
		return runForeverThread;
	}
	protected void setRunForeverThread(Thread runForeverThread) {
		runForeverThread.setDaemon(true);
		this.runForeverThread = runForeverThread;
	}

	public String getSelectedActionType(){
		ObservableList<Integer> list = this.getListView().getSelectionModel().getSelectedIndices();
		if(list.size() == 0 || list.size() > 1){
			return "";
		} else{
			return this.getActionList().get(list.get(0)).getActionAsString();
		}
	}

	private class ActionThread extends Thread{
		@Override
		public void run(){
			synchronized(ActionQueue.this.getListView()){
				try {
					ActionQueue.this.runSequence();
				} catch (InterruptedException e) {
					Log.log(e, Log.Level.TRACE);
				} finally{
					ActionQueue.this.getListView().notifyAll();
				}

			}
		}
	}

	private class ActionLoopThread extends Thread{
		@Override
		public void run(){
			synchronized(ActionQueue.this.getListView()){
				try{
					while(true){
						ActionQueue.this.runSequence();
					}
				} catch(InterruptedException e){
					Log.log(e, Log.Level.TRACE);
				} finally{
					ActionQueue.this.getListView().notifyAll();
				}
			}
		}
	}
}