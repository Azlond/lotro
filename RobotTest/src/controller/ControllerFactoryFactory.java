package controller;

import javafx.util.Callback;
import util.Log;

public final class ControllerFactoryFactory {

	private static final DoubleClickController doubleClickController = new DoubleClickController();
	private static final ClickController clickController = new ClickController();
	private static final KeyController keyController = new KeyController();
	private static final WaitController waitController = new WaitController();
	private static final LoopStartController loopStartController = new LoopStartController();
	private static final LoopEndController loopEndController = new LoopEndController();

	public static Callback<Class<?>, Object> getControllerFactory(){
		return (param) -> {
			if(param == DoubleClickController.class){
				return doubleClickController;
			}
			if(param == ClickController.class){
				return clickController;
			}
			if(param == KeyController.class){
				return keyController;
			}
			if(param == WaitController.class){
				return waitController;
			}
			if(param == LoopStartController.class){
				return loopStartController;
			}
			if(param == LoopEndController.class){
				return loopEndController;
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
		controller.setLoopStartController(loopStartController);
		controller.setLoopEndController(loopEndController);
	}
}
