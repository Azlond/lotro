package controller;

import data.Keys;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.ActionObject;
import model.WaitAction;

public class WaitController extends SubController{
	@FXML
	private TextField tfDuration;
	@FXML
	private GridPane paneWait;

	public StringProperty tfDuration(){
		return tfDuration.textProperty();
	}

	@Override
	public String getAction() {
		return Keys.action_wait;
	}

	@Override
	public ActionObject getActionObject() {
		return new WaitAction(Integer.valueOf(this.tfDuration().getValue()));
	}

	@Override
	public void addEventFilters(Stage stage) {
		//nothing to do
	}

	@Override
	public void removeEventFilters(Stage stage) {
		//nothing to do
	}

	@Override
	public void addEditEventFilters(Stage stage) {
		//nothing to do
	}

	@Override
	public void removeEditEventFilters(Stage stage) {
		//nothing to do
	}
}
