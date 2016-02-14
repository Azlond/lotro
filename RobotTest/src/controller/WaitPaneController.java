package controller;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import model.ActionObject;
import model.WaitAction;
import data.Keys;

public class WaitPaneController extends SubController{
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
	public void addEventFilters() {
		//nothing to do
	}

	@Override
	public void removeEventFilters() {
		//nothing to do
	}
}
