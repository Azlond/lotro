package controller;

import data.Keys;
import gui.EventHandlerFactory;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.ActionObject;
import model.ClickAction;

public class ClickPaneController extends SubController{
	@FXML
	protected TextField tfClickX, tfClickY;
	
	protected EventHandler<MouseEvent> eventHandler;
	
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
	
	/*
	 * needed for loading XXX
	 */
	public ActionObject getActionObject(int x, int y) {
		return new ClickAction(x,y);
	}
	
	@Override
	public void addEventFilters(Stage stage) {
		stage.addEventFilter(MouseEvent.MOUSE_DRAGGED, this.getEventHandler());
	}

	@Override
	public void removeEventFilters(Stage stage) {
		stage.removeEventFilter(MouseEvent.MOUSE_DRAGGED, this.getEventHandler());
	}
	
	protected EventHandler<MouseEvent> getEventHandler(){
		if(eventHandler == null){
			eventHandler = EventHandlerFactory.getFactory().getMouseDragEventHandler(tfClickX, tfClickY);
		}
		return eventHandler;
	}
}
