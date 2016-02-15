package controller;

import javafx.util.Callback;
import util.Log;

public final class ControllerFactoryFactory {
	
	private static final DoubleClickController doubleClickController = new DoubleClickController();
	private static final ClickPaneController clickController = new ClickPaneController();
	private static final KeyPaneController keyController = new KeyPaneController();
	private static final WaitPaneController waitController = new WaitPaneController();
	
	public static Callback<Class<?>, Object> getControllerFactory(){
		return (param) -> {
			if(param == DoubleClickController.class){
				return doubleClickController;
			}
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
			} catch (InstantiationException | IllegalAccessException e) { //run as fast as you can, because this is certain death
				Log.log(e, Log.Level.FATAL);
				return null;
			}
		};
	}
	
	public static void initSubControllers(StartController controller){
		controller.setClickController(clickController);
		controller.setKeyController(keyController);
		controller.setWaitController(waitController);
		controller.setDoubleClickController(doubleClickController);
	}
}
