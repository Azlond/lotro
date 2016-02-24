package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import data.Keys;
import gui.ListenerFactory;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.ActionObject;
import model.ActionQueue;
import util.Log;
import util.Util;

public class StartController implements Initializable {

	@FXML
	private Button btnUp, btnDown, btnDelete, btnDuplicate, btnAdd, btnRun, btnRunForever, btnStop, btnEdit;
	@FXML
	private ComboBox<String> comboAction;
	@FXML
	private ListView<String> lvActions;
	@FXML
	private BorderPane paneLayout;
	@FXML
	private AnchorPane paneParameter;
	@FXML
	private TextField tfTimes;
	@FXML
	private Label lbDiscardChanges, lbSaveChanges;
	@FXML
	private CheckBox cbSelection;

	private KeyController keyController;
	private WaitController waitController;
	private ClickController clickController;
	private DoubleClickController doubleClickController;
	private LoopStartController loopStartController;
	private LoopEndController loopEndController;

	private ActionQueue actionQueue;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// Combobox Items setzen und Listener bzgl. Auswahl setzen
		final BooleanProperty selectionOnly = cbSelection.selectedProperty();
		Platform.runLater(() -> {
			comboAction.itemsProperty().set(FXCollections.observableArrayList(Keys.getActions()));
			comboAction.getSelectionModel().selectedItemProperty().addListener(ListenerFactory.getComboActionsListener(paneParameter, this));
			comboAction.setValue(Keys.getActions().get(0));
			this.setActionQueue(new ActionQueue(lvActions));
			lvActions.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			this.getActionQueue().setSelectionOnly(selectionOnly);
		});
	}

	/**
	 * saving a chosen configuration
	 * 
	 * @param event
	 */
	@FXML
	protected void saveToFile(ActionEvent event) {

		FileChooser saveFile = new FileChooser();
		FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("Serialization files (*.ser)", "*.ser");
		saveFile.getExtensionFilters().add(txtFilter);
		File file = saveFile.showSaveDialog(null);
		if (file != null) {
			try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))){

				out.writeObject(this.getActionQueue().getActionList());

			} catch (IOException ex) {
				Log.log(ex);
			}
		}

	}

	/**
	 * loading the saved configuration
	 * 
	 * @param event
	 */
	@SuppressWarnings("unchecked")
	@FXML
	protected void loadFromFile(ActionEvent event) {

		FileChooser openFile = new FileChooser();
		FileChooser.ExtensionFilter txtFilter = new FileChooser.ExtensionFilter("Serialization files (*.ser)", "*.ser");
		openFile.getExtensionFilters().add(txtFilter);
		File file = openFile.showOpenDialog(null);

		if (file != null) {
			try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
				ArrayList<ActionObject> loadedActionList = (ArrayList<ActionObject>) in.readObject();
				this.getActionQueue().setActionList(loadedActionList);

				this.getActionQueue().getDisplayList().clear();

				for (ActionObject action : this.getActionQueue().getActionList()) {
					this.getActionQueue().getDisplayList().add(action.getActionString());
				}
			} catch (ClassNotFoundException | IOException e) {
				Log.log(e);
			}
		}
	}

	@FXML
	protected void runForever(ActionEvent event) {
		Util.getDaemon(() -> {
			this.disableListButtons();
			this.getActionQueue().runForever();
		}).start();
	}

	protected void disableListButtons() {
		this.lock(lvActions, true, btnAdd, btnUp, btnDown, btnDelete, btnDuplicate, btnEdit);
	}

	@FXML
	protected void stop(ActionEvent event) {
		this.getActionQueue().stop();
	}

	@FXML
	protected void runActionQueue(ActionEvent event) {
		Util.getDaemon(() -> {
			int times = 1;
			try{
				times = Integer.parseInt(tfTimes.getText());
			} catch(NumberFormatException e){
				Log.log("StartController.runActionQueue - times is NaN", Log.Level.DEBUG);
			}
			this.disableListButtons();
			this.getActionQueue().run(times);
		}).start();
	}

	@FXML
	protected void moveUp(ActionEvent event) {
		synchronized (lvActions) {
			this.getActionQueue().moveItems(1);
		}
	}

	@FXML
	protected void moveDown(ActionEvent event) {
		synchronized (lvActions) {
			this.getActionQueue().moveItems(-1);
		}
	}

	@FXML
	protected void deleteSelected(ActionEvent event) {
		synchronized (lvActions) {
			this.getActionQueue().removeSelectedItems();
		}
	}

	@FXML
	protected void duplicateSelected(ActionEvent event) {
		synchronized (lvActions) {
			this.getActionQueue().duplicateSelectedItems();
		}
	}

	@FXML
	protected void addSequenceElement(ActionEvent event) {
		synchronized (lvActions) {
			ActionObject newAction = this.getSubController().getActionObject();
			this.getActionQueue().addItem(newAction);
		}
	}

	@FXML
	protected void editAction(ActionEvent event){
		if(this.getActionQueue().getSelectedActionType().length() > 0){ //ist genau ein Item ausgewählt?
			this.disableListButtons();
			this.lockAction();
			this.lock(lvActions, true, lvActions);
			this.disableRunControl();

			lbDiscardChanges.setVisible(true);
			lbSaveChanges.setVisible(true);
		}
	}

	@FXML
	protected void discardChanges(MouseEvent event){ //nothing happens, but all the locks are being lifted´
		if(event.getButton() != MouseButton.SECONDARY){
			return;
		}
		lbDiscardChanges.setVisible(false);
		lbSaveChanges.setVisible(false);
		Util.getDaemon(() -> {
			synchronized(comboAction){
				comboAction.notifyAll();
			}
			synchronized(lvActions){
				lvActions.notifyAll();
			}
		}).start();
	}

	@FXML
	protected void saveChanges(MouseEvent event){
		if(event.getButton() != MouseButton.SECONDARY){
			return;
		}
		synchronized(lvActions){
			ActionObject editedAction = this.getSubController().getActionObject();
			this.getActionQueue().replaceSelectedAction(editedAction);
		}
		this.discardChanges(event); //does nothing but enabling everything again
	}

	public KeyController getKeyController() {
		return keyController;
	}
	public void setKeyController(KeyController keyController) {
		this.keyController = keyController;
	}

	public WaitController getWaitController() {
		return waitController;
	}
	public void setWaitController(WaitController waitController) {
		this.waitController = waitController;
	}

	public ClickController getClickController() {
		return clickController;
	}
	public void setClickController(ClickController clickController) {
		this.clickController = clickController;
	}

	public DoubleClickController getDoubleClickController() {
		return doubleClickController;
	}
	public void setDoubleClickController(DoubleClickController doubleClickController) {
		this.doubleClickController = doubleClickController;
	}

	public LoopStartController getLoopStartController() {
		return loopStartController;
	}
	public void setLoopStartController(LoopStartController loopStartController) {
		this.loopStartController = loopStartController;
	}

	public LoopEndController getLoopEndController() {
		return loopEndController;
	}
	public void setLoopEndController(LoopEndController loopEndController) {
		this.loopEndController = loopEndController;
	}

	/**
	 * @return the SubController which matches the currently selected action
	 */
	public SubController getSubController() {
		return this.getSubControllerFor(comboAction.getValue());
	}

	/**
	 * @param action
	 *            the action String
	 * @return the corresponding SubController for the action
	 */
	public SubController getSubControllerFor(String action) {
		switch (action) {
			case Keys.action_click:
				return this.getClickController();
			case Keys.action_doubleClick:
				return this.getDoubleClickController();
			case Keys.action_key:
				return this.getKeyController();
			case Keys.action_wait:
				return this.getWaitController();
			case Keys.action_loopStart:
				return this.getLoopStartController();
			case Keys.action_loopEnd:
				return this.getLoopEndController();
			default:
				Log.log("Selected action doesn't match any pre-defined actions (StartController.getSubController())", Log.Level.FATAL);
				return null;
		}
	}

	protected ActionQueue getActionQueue() {
		return actionQueue;
	}

	protected void setActionQueue(ActionQueue actionList) {
		this.actionQueue = actionList;
	}

	/**
	 * switches the value of the action combobox to the one of the currently selected item in the listView<br/>
	 * if none or multiple items are selected, nothing happens<br/>
	 * the combobox remains disabled until comboAction.notify is called
	 */
	protected void lockAction(){
		String item = this.getActionQueue().getSelectedActionType();
		if(item.length() == 0){
			return;
		}
		comboAction.getSelectionModel().select(item);
		this.getSubController().removeEventFilters(this.getStage());
		this.getSubController().addEditEventFilters(this.getStage());
		this.lock(comboAction, true, comboAction);
		this.lockExecution(comboAction, () -> {
			this.getSubController().removeEditEventFilters(this.getStage());
			this.getSubController().addEventFilters(this.getStage());
		});
	}

	protected void disableRunControl(){
		this.lock(lvActions, true, btnRun, btnRunForever, btnStop, tfTimes, cbSelection);
	}

	/**
	 * @param lock the object which acts as a lock. nodes will remain disabled/enabled, until lock.notify() is called
	 * @param disabledForDuration
	 * @param nodes Nodes to be enabled/disabled
	 */
	protected void lock(Object lock, boolean disabledForDuration, Node...nodes){
		for(Node node : nodes){
			node.setDisable(disabledForDuration);
		}
		Util.getDaemon(() ->{
			try {
				synchronized(lock){
					lock.wait();
				}
			} catch (Exception e) {
				Log.log(e);
			} finally{
				Platform.runLater(() -> { //only FX Thread may modify GUI elements
					for(Node node : nodes){
						node.setDisable(!disabledForDuration);
					}
				});
			}
		}).start();
	}

	protected Stage getStage(){
		return (Stage)btnAdd.getScene().getWindow();
	}

	protected void lockExecution(Object lock, Runnable execution){
		Util.getDaemon(() ->{
			try {
				synchronized(lock){
					lock.wait();
				}
			} catch (Exception e) {
				Log.log(e);
			} finally{
				Platform.runLater(() -> { //only FX Thread may modify GUI elements. since Runnable probably contains GUI, better be safe
					execution.run();
				});
			}
		}).start();
	}
}
