package model;

public class DoubleClickAction extends ClickAction {

	public DoubleClickAction(double x, double y) {
		super(x, y);
		this.setAction(Action.doubleclick);
	}

	@Override
	public void perform() throws InterruptedException{
		super.perform();
		super.perform();
	}

	@Override
	public String getActionString() {
		return "doubleclick at (" + this.getX() + "|" + this.getY() + ")";
	}

	@Override
	public ActionObject getCopy(){
		return super.getCopy();
	}
}
