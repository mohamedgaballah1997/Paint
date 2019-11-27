package paint.controller;

public class GuiState {
	private State state;
	private Engine eng;
	
	public GuiState()
	{
		this.eng=Engine.getEngine();
		state=null;
	}

	
	public State getState() {
		return state;	
		
	}
	public boolean ifSelected() {
		if(eng.getSelectedShapes().isEmpty())
			return false;
		return true;
	}

	public void setState(State state) {
	      this.state = state;	
	}
	

}
