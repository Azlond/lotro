package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class KeyPaneController extends Controller {
	@FXML
	private TextField tfKey;
	@FXML
	private Label lbKey;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
	}
	
	public StringProperty tfKey(){
		return tfKey.textProperty();
	}
	
	public StringProperty lbKey(){
		return lbKey.textProperty();
	}
}
