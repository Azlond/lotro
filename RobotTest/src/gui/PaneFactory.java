package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class PaneFactory {
	private static Pane waitPane, clickPane, keyPane;
	public static Pane getWaitPane() throws IOException{
		if(waitPane == null){
			FXMLLoader loader = new FXMLLoader(PaneFactory.class.getResource("/view/WaitPane.fxml"));
			waitPane = loader.load();
		}
		return waitPane;
	}
	
	public static Pane getClickPane() throws IOException{
		if(clickPane == null){
			FXMLLoader loader = new FXMLLoader(PaneFactory.class.getResource("/view/ClickPane.fxml"));
			clickPane = loader.load();
		}
		return clickPane;
	}
	
	public static Pane getDoubleClickPane() throws IOException{
		return getClickPane();
	}
	
	public static Pane getKeyPane() throws IOException{
		if(keyPane == null){
			FXMLLoader loader = new FXMLLoader(PaneFactory.class.getResource("/view/KeyPane.fxml"));
			keyPane = loader.load();
		}
		return keyPane;
	}
}
