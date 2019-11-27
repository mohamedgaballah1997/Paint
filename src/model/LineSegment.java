package paint.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.HashMap;

public class LineSegment extends MyShape {
	public LineSegment () {
		super();
	}
	
	public LineSegment (Point p1,Point p2) {
		super();
		setPosition(p1);
		prop.put("x2", p2.getX());
		prop.put("y2", p2.getY());
	}

	public void setLast(Point last) {
		prop.put("x2", last.getX());
		prop.put("y2", last.getY());
	}
	public Point getLast() {
		return new Point(prop.get("x2").intValue(),(int) prop.get("y2").intValue());
	}
	@Override
    public void setProperties(java.util.Map<String, Double> properties) {
        prop = properties;
    }

    @Override
    public java.util.Map<String, Double> getProperties() {
        return prop;
    }	
	
	
	public void draw(Object canvas) {
//	((java.awt.Graphics)canvas).drawLine((int)position.getX(),(int)position.getY(),prop.get("x2").intValue(),prop.get("y2").intValue());
	Graphics g=(Graphics) canvas;
	 Graphics2D g2d= (Graphics2D) g.create();
	 g2d.setColor(bordColor);
	 g2d.setStroke(new BasicStroke(3));
		g2d.drawLine((int)position.getX(),(int)position.getY(),prop.get("x2").intValue(),prop.get("y2").intValue());
	
	
	}
	
	@Override
	public void drawDashed(Object canvas) {
		 Graphics g=(Graphics) canvas;
		 Graphics2D g2d= (Graphics2D) g.create();
		 Stroke dashed=new BasicStroke(3,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,0,new float[] {9},0);
		 g2d.setStroke(dashed);
		 g2d.drawLine((int)position.getX(),(int)position.getY(),prop.get("x2").intValue(),prop.get("y2").intValue());

		
	}
	 public Object clone(){
		 Shape s = new LineSegment();
		 s.setPosition(position);
		 s.setColor(bordColor);
		 s.setFillColor(inColor);
		 s.setProperties(new HashMap<>(prop));
		 return s;
	 }

}
