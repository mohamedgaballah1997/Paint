package paint.model;

import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class xml implements SaveLoad{

	@Override
	public void save(String path, LinkedList<Shape> shapes) {

		  try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = docBuilder.newDocument();
	                Element Shapes = doc.createElement("Shapes");
	                  doc.appendChild(Shapes);
	              for (Shape shape: shapes) {
	            	  Element element = doc.createElement("Shape");
	            	  if(shape instanceof Circle) {
	            	  element.setAttribute("Type", "Circle");
	                  Shapes.appendChild(element);
	                  Element radius=doc.createElement("properties");
	                  radius.appendChild(doc.createTextNode(""+shape.getProperties().get("radius")));
	            	  element.appendChild(radius);
	            	  }
	            	  else if(shape instanceof Ellipse) {
	            		  element.setAttribute("Type", "Ellipse");
		                  Shapes.appendChild(element);
		                  Element properties=doc.createElement("properties");
		                  properties.appendChild(doc.createTextNode(""+shape.getProperties().get("width")+","+shape.getProperties().get("height")));
		            	  element.appendChild(properties);
	            	  }
	            	  else if(shape instanceof Rectangle) {
	            		  element.setAttribute("Type", "Rectangle");
		                  Shapes.appendChild(element);
		                  Element properties=doc.createElement("properties");
		                  properties.appendChild(doc.createTextNode(""+shape.getProperties().get("length")+","+shape.getProperties().get("width")));
		            	  element.appendChild(properties);
	            	  }
	            	  else if(shape instanceof Square) {
	            		  element.setAttribute("Type", "Square");
		                  Shapes.appendChild(element);
		                  Element properties=doc.createElement("properties");
		                  properties.appendChild(doc.createTextNode(""+shape.getProperties().get("side")));
		            	  element.appendChild(properties);
	            	  }
	            	  else if(shape instanceof Triangle) {
	            		  element.setAttribute("Type", "Triangle");
		                  Shapes.appendChild(element);
		                  Element properties=doc.createElement("properties");
		                  properties.appendChild(doc.createTextNode(""+shape.getProperties().get("x2")+","+shape.getProperties().get("y2")));
		                  element.appendChild(properties);
	            	  }
	            	  else if(shape instanceof LineSegment) {
	            		  element.setAttribute("Type", "Line");
		                  Shapes.appendChild(element);
		                  Element properties=doc.createElement("properties");
		                  properties.appendChild(doc.createTextNode(""+shape.getProperties().get("x2")+","+shape.getProperties().get("y2")));
		                  element.appendChild(properties);
	            	  }
	            	  Element color=doc.createElement("color");
	                  color.appendChild(doc.createTextNode(""+shape.getColor().getRGB()));
	            	  element.appendChild(color);
	            	  Element fillcolor=doc.createElement("fillcolor");
	                  fillcolor.appendChild(doc.createTextNode(""+shape.getFillColor().getRGB()));
	            	  element.appendChild(fillcolor);
	            	  Element position=doc.createElement("position");
	            	  Element xCoordinate=doc.createElement("xCoordinate");
	                  xCoordinate.appendChild(doc.createTextNode(""+shape.getPosition().x));
	            	  position.appendChild(xCoordinate);
	            	  Element yCoordinate=doc.createElement("yCoordinate");
	                  yCoordinate.appendChild(doc.createTextNode(""+shape.getPosition().y));
	            	  position.appendChild(yCoordinate);
	                  element.appendChild(position);
	              }
			// write the content into xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(path));
			transformer.transform(source, result);
		  } catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		  } catch (TransformerException tfe) {
			tfe.printStackTrace();
		  }
		
	}

	@Override
	public LinkedList<Shape> load(String path) {
		LinkedList<Shape> l=new LinkedList<>();
		try {
			File file = new File(path);
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
			
			Document document = documentBuilder.parse(file);
			                for (int i = 0; i < document.getElementsByTagName("Shape").getLength(); i++) {
			                	Node shapeNode=document.getElementsByTagName("Shape").item(i);
			            		String properties=document.getElementsByTagName("properties").item(i).getTextContent();
			            		String[] prop=properties.split(",");
			            		Map<String, Double> m=new HashMap<>();
			            		Shape shape = null;
			  
			if(shapeNode.getAttributes().getNamedItem("Type").getNodeValue().equals("Circle")) {
				shape=new Circle();
				m.put("radius", Double.parseDouble(prop[0]));
				shape.setProperties(m);
			}
			else if(shapeNode.getAttributes().getNamedItem("Type").getNodeValue().equals("Square")) {
				shape=new Square();
				m.put("side", Double.parseDouble(prop[0]));
				shape.setProperties(m);
			}
			else if(shapeNode.getAttributes().getNamedItem("Type").getNodeValue().equals("Triangle")) {
				shape= new Triangle();
				Point p2=new Point((int)Double.parseDouble(prop[0]), (int)Double.parseDouble(prop[1]));
				((Triangle)shape).setP2(p2);
			}
			else if(shapeNode.getAttributes().getNamedItem("Type").getNodeValue().equals("Ellipse")) {
				shape=new Ellipse();
				m.put("width", Double.parseDouble(prop[0]));
				m.put("height", Double.parseDouble(prop[1]));
				shape.setProperties(m);
			}
			else if(shapeNode.getAttributes().getNamedItem("Type").getNodeValue().equals("Line")) {
				shape=new LineSegment();
				m.put("x2", Double.parseDouble(prop[0]));
				m.put("y2", Double.parseDouble(prop[1]));
				shape.setProperties(m);
			}
			else if(shapeNode.getAttributes().getNamedItem("Type").getNodeValue().equals("Rectangle")) {
				shape=new Rectangle();
				m.put("length", Double.parseDouble(prop[0]));
				m.put("width", Double.parseDouble(prop[1]));
				shape.setProperties(m);
			}
			Point position=new Point();
			position.x=Integer.parseInt(document.getElementsByTagName("xCoordinate").item(i).getTextContent());
			position.y=Integer.parseInt(document.getElementsByTagName("yCoordinate").item(i).getTextContent());
			shape.setPosition(position);
			shape.setColor(new Color(Integer.parseInt(document.getElementsByTagName("color").item(i).getTextContent())));
			shape.setFillColor(new Color(Integer.parseInt(document.getElementsByTagName("fillcolor").item(i).getTextContent())));
			l.add(shape);
			       }
			            } catch (ParserConfigurationException ex) {
			            	ex.getStackTrace();
			            } catch (SAXException ex) {
			            	ex.getStackTrace();
			            } catch (IOException ex) {
			            	ex.getStackTrace();
			            }
		return l;
	}

}
