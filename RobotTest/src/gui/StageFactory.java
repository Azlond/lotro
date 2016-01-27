package gui;

import data.Ids;
import data.Keys;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StageFactory {
	public static Stage getMainStage(){
		Stage stage = new Stage();
		return getMainStage(stage);
	}
	
	public static Stage getMainStage(Stage stage){
		stage.setTitle("Lotro Master");
		VBox layout = new VBox();
		HBox layoutForContent = new HBox();
		
		VBox leftLayout = createLeftLayout();
		HBox rightLayout = createRightLayout();
		layoutForContent.getChildren().addAll(leftLayout, rightLayout);
		
		//Menüs
		MenuBar menuBar = new MenuBar();
		menuBar.prefWidthProperty().bind(stage.widthProperty());
		menuBar.setUseSystemMenuBar(true);
		layout.getChildren().addAll(menuBar, layoutForContent);
		
		Menu file = new Menu("File");
		MenuItem newFile = new MenuItem("New");
		newFile.setOnAction(EventHandlerFactory.getNewEventHandler());
		MenuItem open = new MenuItem("Open");
		MenuItem save = new MenuItem("Save");
		MenuItem exit = new MenuItem("Exit");
		file.getItems().addAll(newFile, open, save, exit);
		menuBar.getMenus().addAll(file);
		
		Scene scene = new Scene(layout, 800, 600);
		stage.setScene(scene);
		stage.sizeToScene();
		return stage;
	}
	
	protected static VBox createLeftLayout(){
		VBox layout = new VBox();
		layout.setSpacing(10);
		
		ComboBox<String> comboActions = new ComboBox<String>(FXCollections.observableArrayList(Keys.getActions()));
		comboActions.setId(Ids.left_comboActions);
		
		GridPane paneParameters = new GridPane();
		paneParameters.setId(Ids.left_paneParameters);
		paneParameters.setPrefHeight(400); //TODO make it %-based
		
		Button btnAdd = new Button("Add");
		btnAdd.setId(Ids.left_btnAdd);
		
		layout.getChildren().addAll(comboActions, paneParameters, btnAdd);
		return layout;
	}
	
	protected static HBox createRightLayout(){
		HBox layout = new HBox();
		return layout;
	}
}
