package model;

public class WaitAction extends ActionObject {
	
	private int duration;

	public WaitAction(int ms) {
		super(Action.wait);
		this.setDuration(ms);
	}

	@Override
	public void perform() throws InterruptedException{
		this.sleep(this.getDuration());
	}
	
	public int getDuration() {
		return duration;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
	@Override
	public String getActionString() {
		return "wait " + this.getDuration() + " ms";
	}
}
