package paint.controller;

import java.awt.Point;

public class EditingState implements State {
	
	private Engine eng;
	private String editType;
	private Object canvas ;
	private Point begin,last;
	
	public String getEditType() {
		return editType;
	}
	public void setEditType(String editType) {
		this.editType = editType;
	}

		
	public EditingState(Object canvas,String edit)
	{
		this.canvas=canvas;
		this.eng=Engine.getEngine();
		editType=edit;
		//if(edit.equalsIgnoreCase("select"))
		//eng.deselectAll();
	}
	@Override
	public void doClicked(Point p) {
		if(editType.equalsIgnoreCase("select"))
			eng.select(p);
		else if(editType.equalsIgnoreCase("deselect"))
			eng.deselect(p);
		
	}
	public void draw() {
		eng.refresh(canvas);
		if(editType.equalsIgnoreCase("move")) {
			eng.moveShape(begin, last, canvas);
		}
		if(editType.equalsIgnoreCase("resize")) {
			eng.resize(begin, last, canvas);
		}
		
	}

	@Override
	public void doPressed(Point p) {
		begin=p;
		
	}

	@Override
	public void doReleased(Point p) {
		last=p;
		if(editType.equals("resize") ||editType.equals("move"))
		eng.saveMemento();
	}

	@Override
	public void doDraged(Point p) {
		last=p;
		/*if (editType.equalsIgnoreCase("move"))
			    eng.moveShape(begin,last,canvas);
				else if (editType.equalsIgnoreCase("resize"))
		           eng.resize(begin,last,canvas);*/
	}

}
