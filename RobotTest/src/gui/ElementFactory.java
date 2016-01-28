package gui;

import data.Ids;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ElementFactory {
	public static void setWaitPane(GridPane pane){
		
		Label lbDuration = new Label("Duration (ms)");
		pane.add(lbDuration, 0, 0);
		
		TextField tfDuration = new TextField();
		tfDuration.setId(Ids.wait_tfDuration);
		pane.add(tfDuration, 0, 1);
	}
	
	public static void setClickPane(GridPane pane){
		int row = 0;
		int col = 0;
		
		Label lbPosition = new Label("Position");
		pane.add(lbPosition, col, row);
		row++;
		
		Label lbX = new Label("x");
		pane.add(lbX, col, row);
		col++;
		
		TextField tfX = new TextField();
		tfX.setId(Ids.click_tfX);
		tfX.setPrefWidth(50);
		pane.add(tfX, col, row);
		row++;
		col = 0;
		
		Label lbY = new Label("y");
		pane.add(lbY, col, row);
		col++;
		
		TextField tfY = new TextField();
		tfY.setId(Ids.click_tfY);
		tfY.setPrefWidth(50);
		pane.add(tfY, col, row);
		row++;
	}
	
	public static void setDoubleClickPane(GridPane pane){
		setClickPane(pane);
	}
	
	public static void setKeyPane(GridPane pane){
		int row = 0;
		int col = 0;
		Label lbKey = new Label("Key");
		pane.add(lbKey, col, row);
		col++;
		
		TextField tfKey = new TextField();
		tfKey.setId(Ids.key_tfKey);
		tfKey.setOnKeyReleased(EventHandlerFactory.getKeyEventHandler(pane));
		pane.add(tfKey, col, row);
		col++;
		
		Label lbKeyValue = new Label();
		lbKeyValue.setId(Ids.key_lbKeyValue);
		pane.add(lbKeyValue, col, row);
		
	}
}
