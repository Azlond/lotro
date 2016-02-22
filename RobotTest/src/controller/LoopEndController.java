package controller;

import data.Keys;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ActionObject;
import model.LoopEndAction;

public class LoopEndController extends SubController {

	@FXML
	private TextField tfName;

	@Override
	public String getAction() {
		return Keys.action_loopEnd;
	}

	@Override
	public ActionObject getActionObject() {
		return new LoopEndAction(tfName.getText());
	}

	@Override
	public void addEventFilters(Stage stage) {
	}

	@Override
	public void removeEventFilters(Stage stage) {
	}

	@Override
	public void addEditEventFilters(Stage stage) {
	}

	@Override
	public void removeEditEventFilters(Stage stage) {
	}

}
