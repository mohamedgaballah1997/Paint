package paint.controller;

import java.util.Stack;

public class CareTaker {
	
	int counter;
	Stack<Memento> UndoS ;
	Stack<Memento> RedoS;
	public CareTaker() {
	    counter=0;
		UndoS = new Stack<Memento>();
		RedoS = new Stack<Memento>();
		}
	
	//In case of Loading//
	public void initialize(Memento m){ 
	      UndoS.clear();
	      RedoS.clear();
	       UndoS.push(m);
	      }

	public void addMemento(Memento m)
		{
			RedoS.clear();
			UndoS.push(m);
			if(UndoS.size()==21)
				UndoS.removeElementAt(0);
		}
		
		public Memento undo()
		{
			if( UndoS.size()>1)
			RedoS.push(UndoS.pop());
			if(RedoS.size()==21)
				RedoS.removeElementAt(0);
			return UndoS.peek();
		}
		
		public Memento redo()
		{
			if(RedoS.size()>0)
				UndoS.push(RedoS.pop());
			if(UndoS.size()>0)
				return UndoS.peek();
		//	else
		   //  return RedoS.peek();
			return null;
		}
}
