package paint.model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.HashMap;

public class Triangle extends MyShape {
	//private Point p2;
	//private Point p3;
	//drawPolygon(int[] xPoints, int[] yPoints, int nPoints)
	public Triangle() {
		super();
	}
	public Triangle(Point p1,Point p2) {
		super();
		setPosition(p1);
		prop.put("x2", p2.getX());
		prop.put("y2", p2.getY());
		Point p3=CaculatePoint3(p1, p2);
		prop.put("x3", p3.getX());
		prop.put("y3", p3.getY());
	}
	public void setP2(Point p2) {
		prop.put("x2", p2.getX());
		prop.put("y2", p2.getY());
		Point p3=CaculatePoint3(getPosition(), p2);
		prop.put("x3", p3.getX());
		prop.put("y3", p3.getY());
	}
	public Point getP2() {
		return new Point(prop.get("x2").intValue(), prop.get("y2").intValue());
	}
	public Point CaculatePoint3(Point begin,Point last) {
		double y=last.getY();
		double x;
		if(begin.getX()<last.getX())
		x=begin.getX()-(Math.sqrt(Math.pow(begin.distance(last), 2)-Math.pow(begin.getY()-y, 2)));
		else 
			x=begin.getX()+(Math.sqrt(Math.pow(begin.distance(last), 2)-Math.pow(begin.getY()-y, 2)));
		return new Point((int)x,(int)y);
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
		int[] xpoints=new int[3];
		xpoints[0]=(int)position.getX();
		xpoints[1]=prop.get("x2").intValue();
		xpoints[2]=prop.get("x3").intValue();
		int[] ypoints=new int[3];
		ypoints[0]=(int)position.getY();
		ypoints[1]=prop.get("y2").intValue();
		ypoints[2]=prop.get("y3").intValue();
		
		
		Graphics g=(Graphics) canvas;
		 Graphics2D g2d= (Graphics2D) g.create();
			g2d.setColor(inColor);
			if(inColor.getRGB()!=Color.WHITE.getRGB())
			g2d.fillPolygon(xpoints,ypoints,3);
		 g2d.setColor(bordColor);
		 g2d.setStroke(new BasicStroke(3));
		g2d.drawPolygon(xpoints,ypoints, 3);	
//((java.awt.Graphics)canvas).drawPolygon(xpoints,ypoints,3);
		}

	@Override
	public void drawDashed(Object canvas) {
		 Graphics g=(Graphics) canvas;
		 Graphics2D g2d= (Graphics2D) g.create();
		 Stroke dashed=new BasicStroke(3,BasicStroke.CAP_BUTT,BasicStroke.JOIN_BEVEL,0,new float[] {9},0);
		 g2d.setStroke(dashed);
			int[] xpoints=new int[3];
			xpoints[0]=(int)position.getX();
			xpoints[1]=prop.get("x2").intValue();
			xpoints[2]=prop.get("x3").intValue();
			int[] ypoints=new int[3];
			ypoints[0]=(int)position.getY();
			ypoints[1]=prop.get("y2").intValue();
			ypoints[2]=prop.get("y3").intValue();
	g2d.drawPolygon(xpoints,ypoints,3);
		
	}
	 public Object clone(){
		 Shape s = new Triangle();
		 s.setPosition(position);
		 s.setColor(bordColor);
		 s.setFillColor(inColor);
		 s.setProperties(new HashMap<>(prop));
		 return s;
	 }

}
