package gui;

import java.io.IOException;

import data.Keys;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.AnchorPane;
import util.Log;

public class ListenerFactory {
	public static ChangeListener<String> getComboActionsListener(AnchorPane pane){
		return new ChangeListener<String>(){
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(pane == null){
					Log.log("getComboActionsListener got null as parameter", Log.Level.FATAL);
				}
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
			}
		};
	}
}
