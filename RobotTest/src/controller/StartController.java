package controller;

import gui.ListenerFactory;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import model.ActionObject;
import model.ActionQueue;
import util.Log;
import data.Keys;

public class StartController implements Initializable {
	
	@FXML
	private Button btnUp, btnDown, btnDelete, btnDuplicate, btnAdd;
	@FXML
	private ComboBox<String> comboAction;
	@FXML
	private ListView<String> lvActions;
	@FXML
	private BorderPane paneLayout;
	@FXML
	private AnchorPane paneParameter;
	
	private KeyPaneController keyController;
	private WaitPaneController waitController;
	private ClickPaneController clickController;
	private DoubleClickController doubleClickController;
	
	private ActionQueue actionList;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Combobox Items setzen und Listener bzgl. Auswahl setzen
		Platform.runLater(() -> {
				comboAction.itemsProperty().set(FXCollections.observableArrayList(Keys.getActions()));
				comboAction.getSelectionModel().selectedItemProperty().addListener(ListenerFactory.getComboActionsListener(paneParameter, this));
				comboAction.setValue(Keys.getActions().get(0));
				this.setActionList(new ActionQueue(lvActions));
				lvActions.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			});
	}

	@FXML
	private void moveUp(ActionEvent event){

	}

	@FXML
	private void moveDown(ActionEvent event){

	}

	@FXML
	private void deleteSelected(ActionEvent event){

	}

	@FXML
	private void duplicateSelected(ActionEvent event){

	}

	@FXML
	private void addSequenceElement(ActionEvent event){
		ActionObject newAction = this.getSubController().getActionObject();
		this.getActionList().addItem(newAction);
	}

	public KeyPaneController getKeyController() {
		return keyController;
	}
	public void setKeyController(KeyPaneController keyController) {
		this.keyController = keyController;
	}

	public WaitPaneController getWaitController() {
		return waitController;
	}
	public void setWaitController(WaitPaneController waitController) {
		this.waitController = waitController;
	}

	public ClickPaneController getClickController() {
		return clickController;
	}
	public void setClickController(ClickPaneController clickController) {
		this.clickController = clickController;
	}

	public DoubleClickController getDoubleClickController() {
		return doubleClickController;
	}
	public void setDoubleClickController(DoubleClickController doubleClickController) {
		this.doubleClickController = doubleClickController;
	}
	
	/**
	 * @return the SubController which matches the currently selected action
	 */
	public SubController getSubController(){
		return this.getSubControllerFor(comboAction.getValue());
	}
	
	/**
	 * @param action the action String
	 * @return the corresponding SubController for the action
	 */
	public SubController getSubControllerFor(String action){
		switch(action){
			case Keys.action_click:
				return this.getDoubleClickController();
			case Keys.action_doubleClick:
				return this.getClickController();
			case Keys.action_key:
				return this.getKeyController();
			case Keys.action_wait:
				return this.getWaitController();
			default:
				Log.log("Selected Action doesn't match any pre-defined actions (StartController.getSubController())", Log.Level.FATAL);
				return null;
		}
	}

	protected ActionQueue getActionList() {
		return actionList;
	}
	protected void setActionList(ActionQueue actionList) {
		this.actionList = actionList;
	}

}