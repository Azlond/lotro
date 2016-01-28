package gui;

import data.Ids;
import data.Keys;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import util.GuiUtil;

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
		GridPane rightLayout = createRightLayout();
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
		layout.setPrefWidth(370);
		layout.setAlignment(Pos.TOP_LEFT);
		layout.setPadding(new Insets(20, 10, 20, 20));
		layout.setSpacing(10);
		
		Label lbActions = new Label("Action");
		ComboBox<String> comboActions = new ComboBox<String>(FXCollections.observableArrayList(Keys.getActions()));
		comboActions.setId(Ids.left_comboActions);
		HBox paneActions = GuiUtil.getWrappedInHBox(lbActions, comboActions);
		paneActions.setSpacing(10);
		
		GridPane paneParameters = new GridPane();
		paneParameters.setId(Ids.left_paneParameters);
		paneParameters.setPrefHeight(400); //TODO make it %-based
		paneParameters.setHgap(10);
		paneParameters.setVgap(10);
		comboActions.getSelectionModel().selectedItemProperty().addListener(ListenerFactory.getComboActionsListener(paneParameters));
		
		Button btnAdd = new Button("Add");
		btnAdd.setId(Ids.left_btnAdd);
		btnAdd.setPrefWidth(layout.getPrefWidth());
		btnAdd.setOnAction(EventHandlerFactory.getAddActionEventHandler());
		
		layout.getChildren().addAll(paneActions, paneParameters, btnAdd);
		return layout;
	}
	
	protected static GridPane createRightLayout(){
		GridPane layout = new GridPane();
		layout.prefWidth(370);
		layout.setHgap(10);
		layout.setVgap(10);
		
		ListView<String> lvSequence = new ListView<String>();
		
		return layout;
	}
}
