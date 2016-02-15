package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.stage.Stage;
import model.ActionObject;

public abstract class SubController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
	
	public abstract String getAction();
	public abstract ActionObject getActionObject();
	public abstract void addEventFilters(Stage stage);
	public abstract void removeEventFilters(Stage stage);
	

}
