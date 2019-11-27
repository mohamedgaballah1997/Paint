package paint.controller;

import java.awt.Point;

public class DirectState implements State{
	//canvas 
	//engine
	//opy delete undo redo save

	Engine eng;
	private String type;
	private Object canvas ;
	private Point begin,last;
	
	public String type() {
		return type;
	}
	public void type(String type) {
		this.type = type;
	}		
	public DirectState(Object canvas,String type) 
	{
		this.canvas=canvas;
		this.eng=Engine.getEngine();
		if(type.equals("undo")) {
			eng.undo();
		}
		else if(type.equals("redo")) {
			eng.redo();
		}
		else if(type.equals("remove")) {
			eng.removeSelcted();
			eng.deselectAll();
		}
		else if (type.equals("copy"))
		{
				try {
					eng.cloneShape();
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				eng.deselectAll();
		}
		 
		
	}
	public void draw() {
		eng.refresh(canvas);
	}
	@Override
	public void doClicked(Point p) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doPressed(Point p) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doReleased(Point p) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void doDraged(Point p) {
		// TODO Auto-generated method stub
		
	}
	
}