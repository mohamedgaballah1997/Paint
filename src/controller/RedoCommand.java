package paint.controller;

public class RedoCommand implements Commander{

	private CareTaker careTaker;
	
	public RedoCommand(CareTaker careTaker)
	{
		this.careTaker=careTaker;
	}

	public Memento execute()
	{
	  return careTaker.redo();
	}
}
