package paint.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.HashMap;

public class Ellipse extends MyShape{
	public Ellipse(){
		super();
	}
	public Ellipse(Point p1,Point p2){
		
		super();
		setPosition(new Point((int)Math.min(p1.getX(), p2.getX()),(int)Math.min(p1.getY(), p2.getY())));
		prop.put("width", Math.abs(p1.getX()-p2.getX()));
		prop.put("height", Math.abs(p1.getY()-p2.getY()));
	}
	public Ellipse(double r1, double r2){
		super();
		prop.put("width", r1);
		prop.put("height", r2);
	}
	
	public void setAxis(double r1, double r2){
		prop.put("width", r1);
		prop.put("height",r2);
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
		 Graphics g=(Graphics) canvas;
		 Graphics2D g2d= (Graphics2D) g.create();
			g2d.setColor(inColor);
			if(inColor.getRGB()!=Color.WHITE.getRGB())
			g2d.fillOval(position.x, position.y,prop.get("width").intValue(),prop.get("height").intValue());
			 g2d.setStroke(new BasicStroke(3));
		 g2d.setColor(bordColor);
		g2d.drawOval(position.x, position.y,prop.get("width").intValue(),prop.get("height").intValue());
	 }

	@Override
	public void drawDashed(Object canvas) {
		 Graphics g=(Graphics) canvas;
		 Graphics2D g2d= (Graphics2D) g.create();
		 Stroke dashed=new BasicStroke(3,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,0,new float[] {9},0);
		 g2d.setStroke(dashed);
		 g2d.drawOval(position.x, position.y,prop.get("width").intValue(),prop.get("height").intValue());
	 
		
	}
	 public Object clone(){
		 Shape s = new Ellipse();
		 s.setPosition(position);
		 s.setColor(bordColor);
		 s.setFillColor(inColor);
		 s.setProperties(new HashMap<>(prop));
		 return s;
	 }
	
}
