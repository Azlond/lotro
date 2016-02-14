package gui;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.scene.layout.AnchorPane;
import util.Log;
import controller.StartController;
import data.Keys;

public class ListenerFactory {
	public static ChangeListener<String> getComboActionsListener(AnchorPane pane, StartController controller){
		return (observable, oldValue, newValue) -> {
			if(pane == null || controller == null){
				Log.log("getComboActionsListener got null as parameter", Log.Level.FATAL);
			}
			//clear parameter pane
			pane.getChildren().removeAll(pane.getChildren());
			
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
				}
			} catch(IOException e){
				Log.log(e);
			}
		};
	}
}
