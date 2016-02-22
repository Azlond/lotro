package gui;

import java.io.IOException;

import controller.ControllerFactoryFactory;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class PaneFactory {
	private static Pane waitPane, clickPane, doubleClickPane, keyPane, loopStartPane, loopEndPane;
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

	public static Pane getLoopStartPane() throws IOException{
		if(loopStartPane == null){
			FXMLLoader loader = new FXMLLoader(PaneFactory.class.getResource("/view/LoopStartPane.fxml"));
			loader.setControllerFactory(ControllerFactoryFactory.getControllerFactory());
			loopStartPane = loader.load();
		}
		return loopStartPane;
	}

	public static Pane getLoopEndPane() throws IOException{
		if(loopEndPane == null){
			FXMLLoader loader = new FXMLLoader(PaneFactory.class.getResource("/view/LoopEndPane.fxml"));
			loader.setControllerFactory(ControllerFactoryFactory.getControllerFactory());
			loopEndPane = loader.load();
		}
		return loopEndPane;
	}
}
