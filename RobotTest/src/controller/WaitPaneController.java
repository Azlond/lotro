package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class WaitPaneController extends Controller{
	@FXML
	private TextField tfDuration;
	@FXML
	private GridPane paneWait;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//nothing to do
	}
	
	public StringProperty tfDuration(){
		return tfDuration.textProperty();
	}
}
