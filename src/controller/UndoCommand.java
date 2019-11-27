package paint.controller;

public class UndoCommand implements Commander {
	private CareTaker careTaker;
		
	public UndoCommand(CareTaker careTaker) {
		this.careTaker= careTaker;
	}

	public Memento execute()
	{
		return careTaker.undo();
	}
}
