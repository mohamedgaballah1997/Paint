package paint.controller;

import java.awt.Point;

public class DrawingState implements State {
	
	private Engine engine;
	private Point begin,arrived;
	private Object canvas;
	private String type;

	
	public Object getCanvas() {
		return canvas;
	}
	public void setCanvas(Object canvas) {
		this.canvas = canvas;
	}
	public DrawingState(Object canvas,String type)
	{
	
		this.engine=Engine.getEngine();
		this.canvas=canvas;
		this.type=type;
		engine.deselectAll();
		
	}
	@Override
	public void doClicked(Point p) {

	}

	@Override
	public void doPressed(Point p) {
		begin=p;
		arrived=p;
	}

	@Override
	public void doReleased(Point p) {
		arrived=p;
		engine.addShape(engine.draw(begin, arrived, canvas, type));
		engine.refresh(canvas);
	}

	@Override
	public void doDraged(Point p) {
		arrived=p;
	}
	public void draw() {
		engine.refresh(canvas);
		engine.draw(begin,arrived,canvas,type);
	}

}
