package controller;

import gui.EventHandlerFactory;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.ActionObject;
import model.ClickAction;
import data.Keys;

public class ClickPaneController extends SubController{
	@FXML
	private TextField tfClickX, tfClickY;
//	@FXML
//	private TextField tfClickY;
	
	private EventHandler<MouseEvent> eventHandler;
	
	public StringProperty tfX(){
		return tfClickX.textProperty();
	}
	
	public StringProperty tfY(){
		return tfClickY.textProperty();
	}

	@Override
	public String getAction() {
		return Keys.action_click;
	}

	@Override
	public ActionObject getActionObject() {
		double x = Double.valueOf(this.tfX().getValue().trim());
		double y = Double.valueOf(this.tfY().getValue().trim());
		return new ClickAction(x, y);
	}
	
	@Override
	public void addEventFilters() {
		Stage stage = (Stage)tfClickX.getScene().getWindow();
		stage.addEventFilter(MouseEvent.MOUSE_DRAGGED, this.getEventHandler());
	}

	@Override
	public void removeEventFilters() {
		Stage stage = (Stage)tfClickX.getScene().getWindow();
		stage.removeEventFilter(MouseEvent.MOUSE_DRAGGED, this.getEventHandler());
	}
	
	protected EventHandler<MouseEvent> getEventHandler(){
		if(eventHandler == null){
			eventHandler = EventHandlerFactory.getFactory().getMouseDragEventHandler(tfClickX, tfClickY);
		}
		return eventHandler;
	}
}
