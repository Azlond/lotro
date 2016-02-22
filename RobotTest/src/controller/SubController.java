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

	/**
	 * @return a String representation of the selected Action (constant from the Keys class) without any Parameter information (e.g. "click")
	 */
	public abstract String getAction();

	/**
	 * @return an ActionObject filled with the values of the GUI Elements
	 */
	public abstract ActionObject getActionObject();

	/**
	 * adds the event filters necessary for the selected action
	 * @param stage
	 */
	public abstract void addEventFilters(Stage stage);

	/**
	 * removes the event filters necessary for the selected action
	 * @param stage
	 */
	public abstract void removeEventFilters(Stage stage);

	/**
	 * adds the event filters necessary to edit the selected action
	 * @param stage
	 */
	public abstract void addEditEventFilters(Stage stage);

	/**
	 * removes the event filters necessary to edit the selected action
	 * @param stage
	 */
	public abstract void removeEditEventFilters(Stage stage);
}
