package data;

import java.util.ArrayList;
import java.util.Arrays;

import model.ActionObject.Action;

public class Keys {
	public static final String action_wait = "wait";
	public static final String action_click = "click";
	public static final String action_key = "press key";
	public static final String action_doubleClick = "double click";
	public static final String action_loopStart = "loop start";
	public static final String action_loopEnd = "loop end";

	public static ArrayList<String> getActions(){
		return new ArrayList<String>(Arrays.asList(new String[]{action_key, action_click, action_doubleClick, action_wait, action_loopStart, action_loopEnd}));
	}

	public static String getStringForAction(Action action){
		switch(action){
			case click:
				return action_click;
			case doubleclick:
				return action_doubleClick;
			case key:
				return action_key;
			case wait:
				return action_wait;
			case loopStart:
				return action_loopStart;
			case loopEnd:
				return action_loopEnd;
			default:
				return "unknown action";
		}
	}
}
