package gui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import controller.ControllerFactoryFactory;
import controller.StartController;

public class Start extends Application {

	public static void main(String[] args){
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(Start.class.getResource("/view/Start.fxml"));
		BorderPane root = loader.load();
		StartController controller = loader.getController();
		ControllerFactoryFactory.initControllers(controller);

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Lotro Master");
		primaryStage.sizeToScene();
		primaryStage.show();
	}


}
