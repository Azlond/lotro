package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import controller.ControllerFactoryFactory;

public class PaneFactory {
	private static Pane waitPane, clickPane, doubleClickPane, keyPane;
	public static Pane getWaitPane() throws IOException{
		if(waitPane == null){
			FXMLLoader loader = new FXMLLoader(PaneFactory.class.getResource("/view/WaitPane.fxml"));
			loader.setControllerFactory(ControllerFactoryFactory.getControllerFactory());
			waitPane = loader.load();
		}
		return waitPane;
	}
	
	public static Pane getClickPane() throws IOException{
		if(clickPane == null){
			FXMLLoader loader = new FXMLLoader(PaneFactory.class.getResource("/view/ClickPane.fxml"));
			loader.setControllerFactory(ControllerFactoryFactory.getControllerFactory());
			clickPane = loader.load();
		}
		return clickPane;
	}
	
	public static Pane getDoubleClickPane() throws IOException{
		if(doubleClickPane == null){
			FXMLLoader loader = new FXMLLoader(PaneFactory.class.getResource("/view/DoubleClickPane.fxml"));
			loader.setControllerFactory(ControllerFactoryFactory.getControllerFactory());
			doubleClickPane = loader.load();
		}
		return doubleClickPane;
	}
	
	public static Pane getKeyPane() throws IOException{
		if(keyPane == null){
			FXMLLoader loader = new FXMLLoader(PaneFactory.class.getResource("/view/KeyPane.fxml"));
			loader.setControllerFactory(ControllerFactoryFactory.getControllerFactory());
			keyPane = loader.load();
		}
		return keyPane;
	}
}
