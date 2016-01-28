package gui;

import data.Ids;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.GuiUtil;

public class EventHandlerFactory {
	public static EventHandler<ActionEvent> getNewEventHandler(){
		return new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				Stage stage = StageFactory.getMainStage();
				stage.show();
			}
		};
	}
	
	public static EventHandler<ActionEvent> getAddActionEventHandler(){
		return new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				//TODO
			}
		};
	}
	
	public static EventHandler<KeyEvent> getKeyEventHandler(Pane pane){
		return new EventHandler<KeyEvent>(){
			@Override
			public void handle(KeyEvent event) {
				TextField tfKey = GuiUtil.lookupTextField(pane, Ids.id(Ids.key_tfKey));
				Label lbKeyValue = (Label) pane.lookup(Ids.id(Ids.key_lbKeyValue));
				tfKey.setText(event.getCharacter());
				lbKeyValue.setText(event.getCode().getName());
			}
		};
	}
}
