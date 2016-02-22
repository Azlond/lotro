package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import controller.KeyController;

public class EventHandlerFactory {
	private static EventHandlerFactory factory = new EventHandlerFactory();
	public static EventHandlerFactory getFactory(){
		return factory;
	}
	
	private EventHandler<ActionEvent> addActionEventHandler;
	public EventHandler<ActionEvent> getAddActionEventHandler(){
		if(addActionEventHandler == null){
			addActionEventHandler = (event) -> {
				
			};
		}
		return addActionEventHandler;
	}
	
	private EventHandler<KeyEvent> keyEventHandler;
	@SuppressWarnings("deprecation")
	public EventHandler<KeyEvent> getKeyEventHandler(TextField tfKey, Label lbKey, KeyController controller){
		if(keyEventHandler == null){
			keyEventHandler = (event) -> {
				tfKey.setText("" + event.getCode().impl_getCode());
				lbKey.setText(event.getCode().getName());
				controller.setKeyEvent(event);
				event.consume();
			};
		}
		return keyEventHandler;
	}

	private EventHandler<MouseEvent> mouseDragEventHandler;
	public EventHandler<MouseEvent> getMouseDragEventHandler(TextField tfX, TextField tfY){
		if(mouseDragEventHandler == null){
			mouseDragEventHandler = (event) -> {
				if(event.getButton() == MouseButton.PRIMARY){
					tfX.setText("" + event.getScreenX());
					tfY.setText("" + event.getScreenY());
				}
			};
		}
		return mouseDragEventHandler;
	}

	
	private EventHandler<MouseEvent> mouseDragDoubleClickEventHandler;
	public EventHandler<MouseEvent> getMouseDragDoubleClickEventHandler(TextField tfX, TextField tfY){
		if(mouseDragDoubleClickEventHandler == null){
			mouseDragDoubleClickEventHandler = (event) -> {
				tfX.setText("" + event.getScreenX());
				tfY.setText("" + event.getScreenY());
			};
		}
		return mouseDragDoubleClickEventHandler;
	}
}
