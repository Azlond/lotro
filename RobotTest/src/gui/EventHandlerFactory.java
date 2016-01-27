package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

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
}
