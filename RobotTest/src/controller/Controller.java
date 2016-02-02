package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;

public class Controller implements Initializable {
	@FXML
	private Button btnUp, btnDown, btnDelete, btnDuplicate, btnAdd;
	@FXML
	private ComboBox<String> comboAction;
	@FXML
	private ListView<String> lvActions;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
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

}
