package paint.controller;

import java.awt.Point;

public class SaveLoadState implements State{
Engine eng;
Object canvas;
public SaveLoadState(String path,String type,Object canvas) {
	super();
	this.eng = Engine.getEngine();
	eng.deselectAll();
	this.canvas=canvas;
	if(type.equals("save"))
		eng.save(path);
	else if(type.equals("load"))
		eng.load(path);
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

