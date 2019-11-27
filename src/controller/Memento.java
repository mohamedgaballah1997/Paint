package paint.controller;

import java.util.LinkedList;
import paint.model.Shape;

public class Memento {
	  private  LinkedList<Shape> state;
	  private  LinkedList<Shape> state1;
		
	  //in order that each Memento doesn't point on the same shapeList.
	  public Memento(LinkedList<Shape> state,LinkedList<Shape> state1)
	  {
		  this.state=new LinkedList<Shape>();
		  this.state1=new LinkedList<Shape>();
		  try {
		  for(Shape s: state) {
				this.state.add((Shape) (s.clone()));		
			}
		  for(Shape s: state1) {
				this.state1.add((Shape) (s.clone()));
			}
		  }
		  catch (CloneNotSupportedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  }
	
	public  LinkedList<Shape> getState()
	  {
		LinkedList<Shape> temp=new LinkedList<>();
		try {
		for(Shape s: state) {
				temp.add((Shape) (s.clone()));
			} 
		}catch (CloneNotSupportedException e) {
				e.printStackTrace();
			}	
		  return temp;
	  }
	  public  LinkedList<Shape> getState1()
	  {
		  {
				LinkedList<Shape> temp=new LinkedList<>();
				try {
				for(Shape s: state1) {
						temp.add((Shape) (s.clone()));
					} 
				}catch (CloneNotSupportedException e) {
						e.printStackTrace();
					}	
				  return temp;
			  }
	  }

	@Override
	public String toString() {
		return "Memento " + state1 + state + "\n";
	}
	  
}
