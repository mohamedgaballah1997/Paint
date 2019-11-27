package paint.model;

import java.util.LinkedList;

public interface SaveLoad {
	
	public void save(String path,LinkedList<Shape> shapes);
	public LinkedList<Shape> load(String path);
	
}
