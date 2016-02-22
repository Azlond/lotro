package gui;

import java.io.IOException;

import controller.StartController;
import data.Keys;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import util.Log;

public class ListenerFactory {
	public static ChangeListener<String> getComboActionsListener(AnchorPane pane, StartController controller){
		return (observable, oldValue, newValue) -> {
			if(pane == null || controller == null){
				Log.log("getComboActionsListener got null as parameter", Log.Level.FATAL);
			}
			//clear parameter pane
			pane.getChildren().removeAll(pane.getChildren());

			Stage stage = (Stage)pane.getScene().getWindow();

			if(oldValue != null){ //before first selection, there is no oldValue
				controller.getSubControllerFor(oldValue).removeEventFilters(stage);
			}

			try{
				switch(newValue){
					case Keys.action_wait:
						pane.getChildren().add(PaneFactory.getWaitPane());
						break;
					case Keys.action_click:
						pane.getChildren().add(PaneFactory.getClickPane());
						break;
					case Keys.action_key:
						pane.getChildren().add(PaneFactory.getKeyPane());
						break;
					case Keys.action_doubleClick:
						pane.getChildren().add(PaneFactory.getDoubleClickPane());
						break;
					case Keys.action_loopStart:
						pane.getChildren().add(PaneFactory.getLoopStartPane());
						break;
					case Keys.action_loopEnd:
						pane.getChildren().add(PaneFactory.getLoopEndPane());
						break;
				}
			} catch(IOException e){
				Log.log(e);
			}

			controller.getSubController().addEventFilters(stage);
		};
	}
}
