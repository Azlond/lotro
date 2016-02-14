package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import model.ActionObject;

public abstract class SubController implements Initializable {

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(() -> {
			this.addEventFilters();
		});
	}
	
	public abstract String getAction();
	public abstract ActionObject getActionObject();
	public abstract void addEventFilters();
	public abstract void removeEventFilters();
	

}
