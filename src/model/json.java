package paint.model;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class json implements SaveLoad {

	//JSONObject is a string key and a value, we want to save how many shapes do we have
	//Each bigObject is formed of the fig number and a JsonObject  which is the shape
	public json() {}
	@Override
	
	public void save(String path, LinkedList<Shape> shapeList) {
		
		    int i;
		    JSONObject jBigObject = new JSONObject();
	        jBigObject.put("noOfShapes", "" + shapeList.size());
	        JSONObject jShapes;
	        for(i=0; i<shapeList.size(); i++)
	        {
	        	    Shape s = shapeList.get(i);
		            jShapes=new JSONObject();
		            jShapes.put("Name", s.getClass().getCanonicalName());
		            jShapes.put("PositionX", s.getPosition().getX());
		            jShapes.put("PositionY", s.getPosition().getY());
		            jShapes.put("Color", s.getColor().getRGB());
		            if (s.getFillColor()!=null)
		            jShapes.put("FillColor", s.getFillColor().getRGB());
		            else
		            	jShapes.put("FillColor", null);
		            jShapes.put("properties", s.getProperties());
		          
		            jBigObject.put("" + i, jShapes);
		        }
		        try {
		            FileWriter file = new FileWriter(path);
		            file.write(jBigObject.toJSONString());
		            file.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
			
	        }
		

	@Override
	public LinkedList<Shape> load(String path) {
		JSONParser jsonParser = new JSONParser();
		LinkedList<Shape> shapes=new LinkedList<Shape>();
      
        try { 
        	File file = new File(path);
            Object object = jsonParser.parse(new FileReader(file));       
            JSONObject jsonObject = (JSONObject) object;
            int size = Integer.parseInt(((String) jsonObject.get("noOfShapes")));
            
            for (int i =0; i <size; i++) {
            	JSONObject  jShapes = (JSONObject) jsonObject.get(i + "");
            	Class o=null;
            	try {
            	  o= Class.forName((String)jShapes.get("Name"));}
            	catch(Exception e) {}
            	if (o!=null) {
            		MyShape s=(MyShape) o.newInstance();
                    s.setColor(new Color(((Number)jShapes.get("Color")).intValue()));
                    if (jShapes.get("FillColor")!=null)
                    s.setFillColor(new Color(((Number)jShapes.get("FillColor")).intValue()));
                    s.setProperties((Map<String, Double>)jShapes.get("properties"));
                    s.setPosition(new Point( ((Number)jShapes.get("PositionX")).intValue(),((Number)jShapes.get("PositionY")).intValue()));
                  shapes.add(s);
            	}
            
            }
        }catch (Exception e) {
            }
		return shapes;
	}
	

}
