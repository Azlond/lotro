package model;

import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Main {
	
	private static Robot robot;
	
	/*public static void main(String[] args) {
		int limit = 150;
		try {
			robot = new Robot();
			robot.setAutoDelay(50);
			waitTime(5000);
//			for(int i = 0; i < limit; i++){
//				doOneField();
//				System.out.println((i+1) + " out of " + limit + " done");
//			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}*/
	
	public static void doOneField() throws InterruptedException{
		robot.mouseMove(1085,577);
		waitTime(512);
		click();
		waitTime(10594);
		robot.mouseMove(910,673);
		waitTime(798);
		rightclick();
		waitTime(4074);
	}
	
	public static void goRepairInGaltrev() throws InterruptedException{
		waitTime(246);
		keyclick(KeyEvent.VK_F10);
		waitTime(475);
		keyclick(KeyEvent.VK_U);
		waitTime(5326);
		robot.mouseMove(882,216);
		waitTime(245);
		click();
		waitTime(245);
		robot.mouseMove(821,598);
		waitTime(245);
		click();
		waitTime(245);
		turnAround();
		
		
	}
	
	public static void doubleClick() throws InterruptedException{
		click();
		click();
	}
	
	public static void click() throws InterruptedException{
		robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
		waitTime(50);
		robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
		waitTime(50);
	}
	
	public static void rightclick() throws InterruptedException{
		robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
		waitTime(50);
		robot.mouseRelease(InputEvent.BUTTON3_DOWN_MASK);
		waitTime(50);
	}
	
	public static void waitTime(long time) throws InterruptedException{
		Thread.sleep(time);
	}
	
	public static void keyclick(int key) throws InterruptedException{
		robot.keyPress(key);
		waitTime(50);
		robot.keyRelease(key);
		waitTime(50);
	}
	
	public static void keyclick(int key, int duration) throws InterruptedException{
		robot.keyPress(key);
		waitTime(duration);
		robot.keyRelease(key);
		waitTime(50);
	}
	
	public static void turnAround() throws InterruptedException{
		keyclick(KeyEvent.VK_A, 1250);
	}
	
	
	
}
