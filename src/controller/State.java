package paint.controller;

import java.awt.Point;

public interface State {
	public void doClicked(Point p);
	public void doPressed(Point p);
	public void doReleased(Point p);
	public void doDraged(Point p);
	public void draw();
	
}
