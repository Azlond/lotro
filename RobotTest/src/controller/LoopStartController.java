package controller;

import data.Keys;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.ActionObject;
import model.LoopStartAction;
import util.Log;

public class LoopStartController extends SubController {

	@FXML
	private TextField tfName, tfIterations;

	@Override
	public String getAction() {
		return Keys.action_loopStart;
	}

	@Override
	public ActionObject getActionObject() {
		int iterations = 0;
		try{
			iterations = Integer.parseInt(tfIterations.getText());
		} catch(NumberFormatException e){
			Log.log("LoopStartController.getActionObject - tfIterations is NaN", Log.Level.DEBUG);
		}
		return new LoopStartAction(tfName.getText(), iterations);
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
