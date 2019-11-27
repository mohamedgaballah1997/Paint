package paint.controller;


import paint.model.Ellipse;
import paint.model.LineSegment;
import paint.model.Rectangle;
import paint.model.Shape;
import paint.model.Square;
import paint.model.Triangle;

import java.awt.Point;

import paint.model.Circle;


public class ShapeFactory {

	public Shape getShape(String type,Point begin,Point last) {
		Shape s = null;
		try {
			if(type.equalsIgnoreCase("circle")) {
			s = new Circle(begin,last);
		}else if(type.equalsIgnoreCase("square")) {
			s = new Square(begin,last);
		}else if(type.equalsIgnoreCase("rectangle")) {
			s =  new Rectangle(begin, last);
		}else if(type.equalsIgnoreCase("triangle")) {
			s = new Triangle(begin, last);			
			
		}else if(type.equalsIgnoreCase("line")) {
		
			s = new LineSegment(begin, last);
		}else {
			s = new Ellipse(begin,last);
		}
		}catch(Exception e) {
			
		}
		
		return s;
	}
	
}


/*if(shapeType.equalsIgnoreCase("square"))
shape=new Square(begin,last);
else if(shapeType.equalsIgnoreCase("rectangle"))
shape=new paint.model.Rectangle(begin,last);
else if(shapeType.equalsIgnoreCase("circle"))
	shape=new Circle(begin,last);
else if(shapeType.equalsIgnoreCase("ellipse"))
	shape=new Ellipse(begin,last);
else if(shapeType.equalsIgnoreCase("ellipse"))
	shape=new Ellipse(begin,last);
else if(shapeType.equalsIgnoreCase("triangle"))
shape=new Triangle(begin,last);
else
	shape=new LineSegment(begin,last);*/