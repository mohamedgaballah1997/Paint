package paint.controller;

import java.awt.Color;
import java.awt.Point;

public class ColorState implements State {
	
	Engine eng;
	private Object canvas ;
	private String coloringType;
	
	public String getColoringType() {
		return coloringType;
	}

	public void setColoringType(String coloringType) {
		this.coloringType = coloringType;
	}

	
	
	public ColorState(Object canvas, String coloringType, Color newColor )
	{
		this.canvas=canvas;
		this.eng=Engine.getEngine();
		if(coloringType.equals("colorInside")) {
			eng.colorInside(newColor);
		}	
		else if(coloringType.equals("colorBorder"))
			{eng.colorBorder(newColor);}
		eng.deselectAll();
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

	@Override
	public void draw() {
		eng.refresh(canvas);
	}

}
