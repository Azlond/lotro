package gui;

import data.Keys;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.GridPane;
import log.Log;

public class ListenerFactory {
	public static ChangeListener<String> getComboActionsListener(GridPane pane){
		return new ChangeListener<String>(){
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				if(pane == null){
					Log.log("getComboActionsListener got null as parameter", Log.Level.FATAL);
				}
				pane.getChildren().removeAll(pane.getChildren());
				
				switch(newValue){
					case Keys.action_wait:
						ElementFactory.setWaitPane(pane);
						break;
					case Keys.action_click:
						ElementFactory.setClickPane(pane);
						break;
					case Keys.action_key:
						ElementFactory.setKeyPane(pane);
						break;
					case Keys.action_doubleClick:
						ElementFactory.setDoubleClickPane(pane);
						break;
				}
			}
		};
	}
}
