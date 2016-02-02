package util;

import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class GuiUtil {
	
	public static StackPane getWrappedInPane(Node node){
		StackPane pane = new StackPane();
		pane.setAlignment(Pos.TOP_LEFT);
		pane.getChildren().add(node);
		return pane;
	}
	
	public static TextField lookupTextField(Scene scene, String id){
		Node result = scene.lookup(id);
		if(result != null && (result instanceof TextField)){
			return (TextField) result;
		} else{
			Log.log("Util.lookupTextField(" + id + ") found null or a not compatible class", Log.Level.WARN);
			return null;
		}
	}
	
	public static TextArea lookupTextArea(Scene scene, String id){
		Node result = scene.lookup(id);
		if(result != null && (result instanceof TextArea)){
			return (TextArea) result;
		} else{
			Log.log("Util.lookupTextArea(" + id + ") found null or a not compatible class", Log.Level.WARN);
			return null;
		}
	}
	
	public static CheckBox lookupCheckBox(Scene scene, String id){
		Node result = scene.lookup(id);
		if(result != null && (result instanceof CheckBox)){
			return (CheckBox) result;
		} else{
			Log.log("Util.lookupCheckBox(" + id + ") found null or a not compatible class", Log.Level.WARN);
			return null;
		}
	}
	
	public static RadioButton lookupRadioButton(Scene scene, String id){
		Node result = scene.lookup(id);
		if(result != null && (result instanceof RadioButton)){
			return (RadioButton) result;
		} else{
			Log.log("Util.lookupRadioButton(" + id + ") found null or a not compatible class", Log.Level.WARN);
			return null;
		}
	}
	
	public static ComboBox<?> lookupComboBox(Scene scene, String id){
		Node result = scene.lookup(id);
		if(result != null && (result instanceof ComboBox)){
			return (ComboBox<?>) result;
		} else{
			Log.log("Util.lookupComboBox(" + id + ") found null or a not compatible class", Log.Level.WARN);
			return null;
		}
	}
	
	public static TextField lookupTextField(Pane pane, String id){
		Node result = pane.lookup(id);
		if(result != null && (result instanceof TextField)){
			return (TextField) result;
		} else{
			Log.log("Util.lookupTextField(" + id + ") found null or a not compatible class", Log.Level.WARN);
			return null;
		}
	}
	
	public static TextArea lookupTextArea(Pane pane, String id){
		Node result = pane.lookup(id);
		if(result != null && (result instanceof TextArea)){
			return (TextArea) result;
		} else{
			Log.log("Util.lookupTextArea(" + id + ") found null or a not compatible class", Log.Level.WARN);
			return null;
		}
	}
	
	public static CheckBox lookupCheckBox(Pane pane, String id){
		Node result = pane.lookup(id);
		if(result != null && (result instanceof CheckBox)){
			return (CheckBox) result;
		} else{
			Log.log("Util.lookupCheckBox(" + id + ") found null or a not compatible class", Log.Level.WARN);
			return null;
		}
	}
	
	public static RadioButton lookupRadioButton(Pane pane, String id){
		Node result = pane.lookup(id);
		if(result != null && (result instanceof RadioButton)){
			return (RadioButton) result;
		} else{
			Log.log("Util.lookupRadioButton(" + id + ") found null or a not compatible class", Log.Level.WARN);
			return null;
		}
	}
	
	public static ComboBox<?> lookupComboBox(Pane pane, String id){
		Node result = pane.lookup(id);
		if(result != null && (result instanceof ComboBox)){
			return (ComboBox<?>) result;
		} else{
			Log.log("Util.lookupComboBox(" + id + ") found null or a not compatible class", Log.Level.WARN);
			return null;
		}
	}
	
	public static Scene getScene(Event event){
		return ((Node)event.getSource()).getScene();
	}
	
	public static ScrollPane getWrappedInScrollPane(Node node, int width, int height){
		ScrollPane wrapper = new ScrollPane();
		wrapper.setContent(node);
		wrapper.setPrefSize(width, height);
		return wrapper;
	}
	
	public static HBox getWrappedInHBox(Node... nodes){
		HBox result = new HBox();
		for(Node node : nodes){
			result.getChildren().add(node);
		}
		return result;
	}
}
