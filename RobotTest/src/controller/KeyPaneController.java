package controller;

import data.Keys;
import gui.EventHandlerFactory;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import model.ActionObject;
import model.KeyAction;
import util.Log;

public class KeyPaneController extends SubController {
	@FXML
	private TextField tfKey;
	@FXML
	private Label lbKey;
	
	private KeyEvent keyEvent;
	private EventHandler<KeyEvent> eventHandler;

	public StringProperty tfKey(){
		return tfKey.textProperty();
	}

	public StringProperty lbKey(){
		return lbKey.textProperty();
	}

	@Override
	public String getAction() {
		return Keys.action_key;
	}

	@Override
	public ActionObject getActionObject() {
		if(this.getKeyEvent() == null){
			Log.log("KeyPaneController.getActionObject(): KeyEvent is null", Log.Level.DEBUG);
			return null;
		}
		
		return new KeyAction(this.getKeyEvent());
	}
	
	/*
	 * Keycode nur per mapping zu erhalten?
	 * 
	 * Bisher noch keine bessere Idee.
	 * 
	 * needed for loading XXX
	 */
	public ActionObject getActionObject(int e) {
		System.out.println(e);
		char a = (char) e;
		String b = a+"";
		KeyEvent event = new KeyEvent(KeyEvent.KEY_PRESSED, null, b, KeyCode.ENTER, false, false, false, false);
		System.out.println(event);
		return new KeyAction(event);
	}

	public KeyEvent getKeyEvent() {
		return keyEvent;
	}
	public void setKeyEvent(KeyEvent keyEvent) {
		this.keyEvent = keyEvent;
	}

	@Override
	public void addEventFilters(Stage stage) {
		stage.addEventFilter(KeyEvent.KEY_PRESSED, this.getEventHandler());
	}

	@Override
	public void removeEventFilters(Stage stage) {
		stage.removeEventFilter(KeyEvent.KEY_PRESSED, this.getEventHandler());
	}
	
	protected EventHandler<KeyEvent> getEventHandler() {
		if(eventHandler == null){
			return EventHandlerFactory.getFactory().getKeyEventHandler(tfKey, lbKey, this);
		}
		return eventHandler;
	}
}
