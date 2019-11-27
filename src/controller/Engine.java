package paint.controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
import paint.model.Circle;
import paint.model.Ellipse;
import paint.model.LineSegment;
import paint.model.MyShape;
import paint.model.SaveLoad;
import paint.model.Shape;
import paint.model.Square;
import paint.model.Triangle;



public class Engine implements DrawingEngine {
	private static Engine eng;
	private LinkedList<Shape> shapeList,selectedShapes;
	private CareTaker ct;
	private ShapeFactory factory;

	/* manage shapes objects */
	private Engine() {
		super();
		shapeList = new LinkedList<Shape>();
		selectedShapes=new LinkedList<Shape>();
		 ct= new CareTaker();
		 ct.addMemento(saveNewListM());
		 
	}

	public static Engine getEngine() {
		if (eng == null) {
			eng = new Engine();
		}
		return eng;
	}
	
	/* redraw all shapes on the canvas */
	@Override
	public void refresh(Object canvas) {
		for(Shape s:selectedShapes) {
			((MyShape) s).drawDashed(canvas);
		}
		for (Shape s : shapeList) {
			s.draw(canvas);
		}
	}

	@Override
	public void addShape(Shape shape) {
		shapeList.addFirst(shape);
		ct.addMemento(saveNewListM());
	}
	

	
	@Override
	public void removeShape(Shape shape) {
		selectedShapes.remove(shape);
	}

	@Override
	public void updateShape (Shape oldShape,Shape newShape) {
		selectedShapes.remove(oldShape);
		selectedShapes.add(newShape);
		//ct.addMemento(saveNewListM());
	}
	
	/* return the created shapes objects */
	@Override
	public Shape[] getShapes() {
		 return (Shape[]) shapeList.toArray();
	}
	
	@Override
	public void undo() {
		Commander command=new UndoCommand(ct);	
	   Memento m = command.execute();
	   if(m!=null)
		   getShapesFromMemento(m);
		
	}
	public void removeSelcted() {
		selectedShapes.clear();
		ct.addMemento(saveNewListM());
	}
	@Override
	public void redo() {
		Commander command =new RedoCommand(ct);		
		   Memento m = command.execute();
		   if(m!=null)
			   getShapesFromMemento(m);
	}
	@Override
	public void save(String path) {
		String classType;
		if(path.endsWith(".xml"))
			classType="xml";
		else
			classType="json";
		 try {
	      	 SaveLoad sl=(SaveLoad) Class.forName("paint.model."+classType).newInstance();	   
	         sl.save(path,shapeList);
		 }catch(Exception e) {e.printStackTrace();}	 
		
	}

	@Override
	public void load(String path) {
		String classType;
		if(path.endsWith(".xml"))
			classType="xml";
		else
			classType="json";
		 try {
	      	 SaveLoad sl=(SaveLoad) Class.forName("paint.model."+classType).newInstance();	   
	        shapeList= sl.load(path);
		 }catch(Exception e) {e.printStackTrace();}	 
		
	}
	public Shape draw(Point begin,Point last,Object canvas,String shapeType) {		
	
		factory = new ShapeFactory();
		Shape shape =factory.getShape(shapeType, begin, last);
		shape.draw(canvas);
		refresh(canvas);
		return shape;
	}
	/*
	 * return the classes (types) of supported shapes that can be dynamically loaded
	 * at runtime (see Part 3)
	 */
	@Override
	public java.util.List<Class<? extends Shape>> getSupportedShapes() {
		return null;
	}

	/* add to the supported shapes the new shape class (see Part 3) */
	@Override
	public void installPluginShape(String jarPath) {
		
	}
	public void moveShape(Point p1,Point p2,Object canvas) {
	
		for(Shape s: selectedShapes) {
			Point temp=new Point(0,0);
			if(s instanceof Circle){
				temp.x =(((Circle) s).getPosition().x +p2.x - p1.x);
				temp.y= (((Circle) s).getPosition().y + p2.y - p1.y);
				((Circle) s).setPosition(temp);
					
			}
			else if(s instanceof Ellipse) {
				temp.x =(((Ellipse) s).getPosition().x +p2.x - p1.x);
				temp.y= (((Ellipse) s).getPosition().y + p2.y - p1.y);
				((Ellipse) s).setPosition(temp);
						}
			else if(s instanceof paint.model.Rectangle) {
				temp.x =(((paint.model.Rectangle) s).getPosition().x +p2.x - p1.x);
				temp.y= (((paint.model.Rectangle) s).getPosition().y + p2.y - p1.y);
				((paint.model.Rectangle) s).setPosition(temp);
						}
			
			else if(s instanceof Square){
				temp.x =(((Square) s).getPosition().x +p2.x - p1.x);
				temp.y= (((Square) s).getPosition().y + p2.y - p1.y);
				((Square) s).setPosition(temp);
							}
			else if(s instanceof LineSegment) {
				temp.x =(((LineSegment) s).getPosition().x +p2.x - p1.x);
				temp.y= (((LineSegment) s).getPosition().y + p2.y - p1.y);
				((LineSegment) s).getProperties().put("x2", ((LineSegment) s).getProperties().get("x2")+p2.getX()-p1.getX());
				((LineSegment) s).getProperties().put("y2", ((LineSegment) s).getProperties().get("y2")+p2.getY()-p1.getY());		
				((LineSegment) s).setPosition(temp);
			}
			else {
				temp.x =(((paint.model.Triangle) s).getPosition().x +p2.x - p1.x);
				temp.y= (((paint.model.Triangle) s).getPosition().y + p2.y - p1.y);
				((paint.model.Triangle) s).getProperties().put("x2", ((paint.model.Triangle) s).getProperties().get("x2")+p2.getX()-p1.getX());
				((paint.model.Triangle) s).getProperties().put("y2", ((paint.model.Triangle) s).getProperties().get("y2")+p2.getY()-p1.getY());
				((paint.model.Triangle) s).getProperties().put("x3", ((paint.model.Triangle) s).getProperties().get("x3")+p2.getX()-p1.getX());
				((paint.model.Triangle) s).getProperties().put("y3", ((paint.model.Triangle) s).getProperties().get("y3")+p2.getY()-p1.getY());
				((paint.model.Triangle) s).setPosition(temp);
		} 
		}
		p1.x=p2.x;
		p1.y=p2.y;
	}
	public void resize(Point p1,Point p2,Object canvas) {
		for(Shape s: selectedShapes) {
			if(s instanceof Circle) {
						if(p2.x>p1.x)
							((Circle) s).setRadius(((Circle) s).getRadius()+p2.distance(p1));
					else
						((Circle) s).setRadius(((Circle) s).getRadius()-p2.distance(p1));
								}			
			else if(s instanceof Ellipse) {
				((paint.model.Ellipse) s).getProperties().put("width", ((Ellipse) s).getProperties().get("width")+p2.getX()-p1.getX());
				((paint.model.Ellipse) s).getProperties().put("height", ((Ellipse) s).getProperties().get("height")+p2.getY()-p1.getY());
						}
			else if(s instanceof paint.model.Rectangle) {
						((paint.model.Rectangle) s).getProperties().put("length", ((paint.model.Rectangle) s).getProperties().get("length")+p2.getX()-p1.getX());
						((paint.model.Rectangle) s).getProperties().put("width", ((paint.model.Rectangle) s).getProperties().get("width")+p2.getY()-p1.getY());		
						}
			
			else if(s instanceof Square){
				if(p2.x>p1.x)
				((Square) s).setSide( ((paint.model.Square) s).getProperties().get("side")+p2.distance(p1));
				else
					((Square) s).setSide( ((paint.model.Square) s).getProperties().get("side")-p2.distance(p1));
							}
			else if (s instanceof LineSegment) {
				((LineSegment) s).getProperties().put("x2", ((LineSegment) s).getProperties().get("x2")+p2.getX()-p1.getX());
				((LineSegment) s).getProperties().put("y2", ((LineSegment) s).getProperties().get("y2")+p2.getY()-p1.getY());		
			}
			else {
					
					((paint.model.Triangle) s).setP2(new Point(((paint.model.Triangle) s).getP2().x+p2.x-p1.x,((paint.model.Triangle) s).getP2().y+p2.y-p1.y));
			}
		}
			p1.x=p2.x;
			p1.y=p2.y;
			
	}
	public void saveMemento() {
		ct.addMemento(saveNewListM());
	}
	public LinkedList<Shape> getShapesList(){
		return shapeList;
	}
	public void select(Point p){
		for (Shape s : shapeList) {
		java.awt.Shape impShape = null;
			if(s instanceof Circle) {
				impShape = new Ellipse2D.Double(s.getPosition().getX(), s.getPosition().getY(), s.getProperties().get("radius"), s.getProperties().get("radius"));
			}			
			else if(s instanceof Ellipse) {
				impShape = new Ellipse2D.Double(s.getPosition().getX(), s.getPosition().getY(), s.getProperties().get("width"), s.getProperties().get("height"));
			}
			else if(s instanceof paint.model.Rectangle) {
				impShape = new Rectangle2D.Double(s.getPosition().getX(), s.getPosition().getY(), s.getProperties().get("length"), s.getProperties().get("width"));
			}
			else if(s instanceof Square) {
				impShape = new Rectangle2D.Double(s.getPosition().getX(), s.getPosition().getY(), s.getProperties().get("side"), s.getProperties().get("side"));
			}

			else if(s instanceof LineSegment) {
				impShape = new Line2D.Double(s.getPosition().getX(), s.getPosition().getY(), s.getProperties().get("x2"), s.getProperties().get("y2"));
				if(impShape!=null && impShape.intersects(p.x-1, p.y-1, 7, 7))
				selectedShapes.addFirst(s);
				}
			else if(s instanceof Triangle){
						int [] xpoints = new int[3];
						int [] ypoints = new int[3];
						xpoints[0]=(int)s.getPosition().x;
						xpoints[1]=s.getProperties().get("x2").intValue();
						xpoints[2]=s.getProperties().get("x3").intValue();
						ypoints[0]=(int)s.getPosition().y;
						ypoints[1]=s.getProperties().get("y2").intValue();
						ypoints[2]=s.getProperties().get("y3").intValue();
						impShape = new Polygon(xpoints, ypoints, 3);
						
						
					}
				if (impShape!=null && impShape.contains(p)) {
						selectedShapes.addFirst(s);
					}	
				}
				for(Shape s: selectedShapes) {
					shapeList.remove(s);
				}
		ct.addMemento(saveNewListM());
	}
	public void deselect(Point p){
		for (Shape s : selectedShapes) {
		java.awt.Shape impShape = null;
			if(s instanceof Circle) {
				impShape = new Ellipse2D.Double(s.getPosition().getX(), s.getPosition().getY(), s.getProperties().get("radius"), s.getProperties().get("radius"));
			}			
			else if(s instanceof Ellipse) {
				impShape = new Ellipse2D.Double(s.getPosition().getX(), s.getPosition().getY(), s.getProperties().get("width"), s.getProperties().get("height"));
			}
			else if(s instanceof paint.model.Rectangle) {
				impShape = new Rectangle2D.Double(s.getPosition().getX(), s.getPosition().getY(), s.getProperties().get("length"), s.getProperties().get("width"));
			}
			else if(s instanceof Square) {
				impShape = new Rectangle2D.Double(s.getPosition().getX(), s.getPosition().getY(), s.getProperties().get("side"), s.getProperties().get("side"));
			}

            else if(s instanceof LineSegment) {
				impShape = new Line2D.Double(s.getPosition().getX(), s.getPosition().getY(), s.getProperties().get("x2"), s.getProperties().get("y2"));
					impShape = new Line2D.Double(s.getPosition().getX(), s.getPosition().getY(), s.getProperties().get("x2"), s.getProperties().get("y2"));
					if(impShape!=null && impShape.intersects(p.x-1, p.y-1, 7, 7))
					shapeList.addFirst(s);
            }
			else {
				int [] xpoints = new int[3];
				int [] ypoints = new int[3];
				xpoints[0]=(int)s.getPosition().x;
				xpoints[1]=s.getProperties().get("x2").intValue();
				xpoints[2]=s.getProperties().get("x3").intValue();
				ypoints[0]=(int)s.getPosition().y;
				ypoints[1]=s.getProperties().get("y2").intValue();
				ypoints[2]=s.getProperties().get("y3").intValue();
				impShape = new Polygon(xpoints, ypoints, 3);
			}
			if(impShape!=null && impShape.contains(p)) {
				shapeList.add(s);
			}								
		}
		for(Shape s: shapeList) {
		selectedShapes.remove(s);
		}
		ct.addMemento(saveNewListM());
	}
	
	public void deselectAll() {
		if(selectedShapes.isEmpty()) return;
		for(Shape s : selectedShapes)
		{
			shapeList.add(s);
		}
		
		selectedShapes.clear();		
		ct.addMemento(saveNewListM());
	}
		
	
	public void colorInside(Color inShapeColor)
	{
		for(Shape s: selectedShapes )
		{
				s.setFillColor(inShapeColor);
		}
		//ct.addMemento(saveNewListM());
	}
	
	public void colorBorder(Color outShapeColor)
	{
		for(Shape s: selectedShapes )
		{
				s.setColor(outShapeColor);
		}
		//ct.addMemento(saveNewListM());
	}
	public Memento saveNewListM()
	{
	//	System.out.println("flag");
		return new Memento(shapeList,selectedShapes);
	}
	
    public void getShapesFromMemento(Memento m){
		   shapeList.clear();
		   shapeList.addAll(m.getState());
		   selectedShapes.clear();
		   selectedShapes.addAll(m.getState1());
    }
    
    
    public void cloneShape() throws CloneNotSupportedException {
		//msh el mafrood ye loop 3al selected?
		
		for (Shape s : selectedShapes) {
			Shape clonedShape = null;
				if(s instanceof Circle) {
					 clonedShape = (Circle)s.clone();
					}			
					else if(s instanceof Ellipse) {
						 clonedShape = (Ellipse)s.clone();
					}
					else if(s instanceof paint.model.Rectangle) {
						 clonedShape = (paint.model.Rectangle)s.clone();
					}
					else if(s instanceof Square) {
					     clonedShape = (Square)s.clone();
					}
					else if(s instanceof LineSegment) {
						 clonedShape = (LineSegment)s.clone(); 
						 clonedShape.getProperties().put("x2", clonedShape.getProperties().get("x2")-clonedShape.getPosition().getX());
						 clonedShape.getProperties().put("y2", clonedShape.getProperties().get("y2")-clonedShape.getPosition().getY());
						}
					else
					{
						 clonedShape = (Triangle)s.clone();
						 Point p=new Point();
						 p.x=(clonedShape.getProperties().get("x2").intValue());
						 p.y=(int) (clonedShape.getProperties().get("y2")-clonedShape.getPosition().getY());
						 ((Triangle) clonedShape).setP2(p);
						 clonedShape.setPosition(new Point(clonedShape.getPosition().x, 0));
						
					}
		Point hey= new Point(0,0);
		if(!(clonedShape instanceof Triangle))
		clonedShape.setPosition(hey);
		shapeList.add(clonedShape);
		}
}

	public LinkedList<Shape> getSelectedShapes() {
		return selectedShapes;
	}
}

