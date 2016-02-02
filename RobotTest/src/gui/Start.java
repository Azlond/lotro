package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Start extends Application {
	
	public static void main(String[] args){
		Application.launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		BorderPane root = (BorderPane) FXMLLoader.load(Start.class.getResource("/view/Start.fxml"));
		
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Lotro Master");
		primaryStage.sizeToScene();
		primaryStage.show();
	}
	
	
}
