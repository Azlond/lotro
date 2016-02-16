package data;

import java.util.ArrayList;
import java.util.Arrays;

public class Keys {
	public static final String action_wait = "wait";
	public static final String action_click = "click";
	public static final String action_key = "press key";
	public static final String action_doubleClick = "double click";
	
	public static ArrayList<String> getActions(){
		return new ArrayList<String>(Arrays.asList(new String[]{action_key, action_click, action_doubleClick, action_wait}));
	}
	
}
