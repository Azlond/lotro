package controller;

import data.Keys;
import gui.EventHandlerFactory;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import model.ActionObject;
import model.DoubleClickAction;

public class DoubleClickController extends ClickPaneController {
	@Override
	public String getAction() {
		return Keys.action_doubleClick;
	}

	@Override
	public ActionObject getActionObject() {
		double x = Double.valueOf(this.tfX().getValue().trim());
		double y = Double.valueOf(this.tfY().getValue().trim());
		return new DoubleClickAction(x, y);
	}
	
	/*
	 * needed for loading XXX
	 */
	public ActionObject getActionObject(int x, int y) {
		return new DoubleClickAction(x, y);
	}

	@Override
	protected EventHandler<MouseEvent> getEventHandler() {
		if(eventHandler == null){
			eventHandler = EventHandlerFactory.getFactory().getMouseDragDoubleClickEventHandler(tfClickX, tfClickY);
		}
		return eventHandler;
	}
	
	
}
