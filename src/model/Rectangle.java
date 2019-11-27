package paint.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.HashMap;

public class Rectangle extends MyShape {
	public Rectangle() {
       super();
	}
	public Rectangle(double length, double width) {
		super();
		prop.put("length", length);
		prop.put("width", width);
	}
	public Rectangle(Point p1, Point p2) {
		super();
		prop.put("length", Math.abs(p1.getX()-p2.getX()));
		prop.put("width", Math.abs(p1.getY()-p2.getY()));
		setPosition(new Point((int)Math.min(p1.getX(), p2.getX()),(int)Math.min(p1.getY(), p2.getY())));
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
			g2d.fillRect(position.x, position.y,prop.get("length").intValue(),prop.get("width").intValue());
		 g2d.setColor(bordColor);
		 g2d.setStroke(new BasicStroke(3));
		g2d.drawRect(position.x, position.y,prop.get("length").intValue(),prop.get("width").intValue());
		//((java.awt.Graphics)canvas).drawRect(position.x, position.y, prop.get("length").intValue(),prop.get("width").intValue() );
	}

	@Override
	public void drawDashed(Object canvas) {
		 Graphics g=(Graphics) canvas;
		 Graphics2D g2d= (Graphics2D) g.create();
		 Stroke dashed=new BasicStroke(3,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,0,new float[] {9},0);
		 g2d.setStroke(dashed);
		 g2d.drawRect(position.x, position.y, prop.get("length").intValue(),prop.get("width").intValue());
	}
	 public Object clone(){
		 Shape s = new Rectangle();
		 s.setPosition(position);
		 s.setColor(bordColor);
		 s.setFillColor(inColor);
		 s.setProperties(new HashMap<>(prop));
		 return s;
	 }
}
