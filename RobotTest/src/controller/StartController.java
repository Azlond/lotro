package controller;

import java.net.URL;
import java.util.ResourceBundle;

import data.Keys;
import gui.ListenerFactory;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class StartController implements Initializable {
	
	private KeyPaneController keyController;
	private WaitPaneController waitController;
	private ClickPaneController clickController;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//Combobox Items setzen und Listener bzgl. Auswahl setzen
		Platform.runLater(new Runnable() {
			@Override
			public void run() {
				comboAction.itemsProperty().set(FXCollections.observableArrayList(Keys.getActions()));
				comboAction.getSelectionModel().selectedItemProperty().addListener(ListenerFactory.getComboActionsListener(paneParameter));
				comboAction.setValue(Keys.getActions().get(0));
			}
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

}
