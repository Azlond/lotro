package gui;


import controller.ClickPaneController;
import controller.KeyPaneController;
import controller.StartController;
import controller.WaitPaneController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import util.Log;

public class Start extends Application {
	
	private final ClickPaneController clickController = new ClickPaneController();
	private final KeyPaneController keyController = new KeyPaneController();
	private final WaitPaneController waitController = new WaitPaneController();
	
	public static void main(String[] args){
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(Start.class.getResource("/view/Start.fxml"));
		Callback<Class<?>, Object> controllerFactory = new Callback<Class<?>, Object>() {
			@Override
			public Object call(Class<?> param) {
				if(param == ClickPaneController.class){
					return clickController;
				}
				if(param == KeyPaneController.class){
					return keyController;
				}
				if(param == WaitPaneController.class){
					return waitController;
				}
				try {
					return param.newInstance();
				} catch (InstantiationException | IllegalAccessException e) {
					Log.log(e);
					return null;
				}
			}
		};
		
		loader.setControllerFactory(controllerFactory);
		BorderPane root = loader.load();
		StartController controller = loader.getController();
		controller.setClickController(clickController);
		controller.setKeyController(keyController);
		controller.setWaitController(waitController);
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Lotro Master");
		primaryStage.sizeToScene();
		primaryStage.show();
	}
	
	
}
