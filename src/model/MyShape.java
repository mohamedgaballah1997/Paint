package paint.model;


import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;


public abstract class MyShape implements Shape{	
	
	    protected Point position;
	    protected Color bordColor;
	    protected Color inColor;
	    protected Map<String, Double> prop ;

	    public MyShape() {
	       position = new Point();
	    	bordColor = Color.BLACK;
	        inColor = Color.WHITE;
	        position.x = 0;
	        position.y = 0;
	        prop = new  HashMap<String,Double>() ;
	      
	    }

	    @Override
	    public void setPosition(java.awt.Point position) {
	        //this.position = (Point)position.clone();
	        this.position = position;
	    }

	    @Override
	    public java.awt.Point getPosition() {
	        return  position;
	    }

	    @Override
	    public void setColor(java.awt.Color color) {
	        bordColor = color;
	    }

	    public void setBordColor(Color c) {
	        bordColor = c;

	    }

	    @Override
	    public java.awt.Color getColor() {
	        return bordColor;

	    }

	    @Override
	    public java.awt.Color getFillColor() {
	        return inColor;
	    }

	    @Override
	    public void setFillColor(java.awt.Color color) {
	        inColor = color;
	    }

	    @Override
	    public void setProperties(java.util.Map<String, Double> properties) {
	        prop = properties;
	    }

	    @Override
	    public java.util.Map<String, Double> getProperties() {
	        return prop;
	    }


	public Object clone() {
		return clone();
		
	}
	public  abstract void drawDashed(Object canvas) ;
/*// TODO Auto-generated method stub
	public Object clone() throws CloneNotSupportedException{
		
	}
	*/



}
