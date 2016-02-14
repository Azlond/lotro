package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import controller.KeyPaneController;

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
	public EventHandler<KeyEvent> getKeyEventHandler(TextField tfKey, Label lbKey, KeyPaneController controller){
		if(keyEventHandler == null){
			keyEventHandler = (event) -> {
				tfKey.setText(event.getCharacter());
				lbKey.setText(event.getCode().getName());
				controller.setKeyEvent(event);
			};
		}
		return keyEventHandler;
	}

	private EventHandler<MouseEvent> mouseDragEventHandler;
	public EventHandler<MouseEvent> getMouseDragEventHandler(TextField tfX, TextField tfY){
		if(mouseDragEventHandler == null){
			mouseDragEventHandler = (event) -> {
				tfX.setText("" + event.getScreenX());
				tfY.setText("" + event.getScreenY());
			};
		}
		return mouseDragEventHandler;
	}
}
