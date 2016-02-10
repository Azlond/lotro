package controller;

import java.net.URL;
import java.util.ResourceBundle;

import gui.EventHandlerFactory;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ClickPaneController extends Controller{
	@FXML
	TextField tfClickX, tfClickY;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Platform.runLater(() -> {
			Stage stage = (Stage)tfClickX.getScene().getWindow();
			stage.addEventFilter(MouseEvent.MOUSE_DRAGGED, EventHandlerFactory.getFactory().getMouseDragEventHandler(tfClickX, tfClickY));
		});
	}
	
	public StringProperty tfX(){
		return tfClickX.textProperty();
	}
	
	public StringProperty tfY(){
		return tfClickX.textProperty();
	}
}
