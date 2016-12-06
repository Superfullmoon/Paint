package drawing;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

/**
 * JPanel pouvant afficher des objets de type Shape
 */
public class DrawingProxy extends Drawing {
	Drawing drawingReal;
	
	public DrawingProxy(Drawing drawing){
		drawingReal = drawing;
	}
	
	public Shape getShape(int id) {
		return drawingReal.getShape(id);
	}
	
	public void setShape(Shape s1, Shape s2) {
		
	}
	
	public Iterator<Shape> iterator(){
		return drawingReal.iterator();
	}
	
	public void addShape(Shape s){
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Shape s : drawingReal){
			s.paint(g);
		}
	}
	
	public void clear(){
		
	}
	
	public int countRectangle() {
		return drawingReal.countRectangle();
	}
	
	public int countCircle() {
		return drawingReal.countCircle();
	}
	
	public void addObserver(ObserverDrawing o) {
		drawingReal.addObserver(o);
	}
	
	public void removeObserver(ObserverDrawing o) {
		drawingReal.removeObserver(o);
	}
	
	public boolean isInGroup(Shape shape) {
		return drawingReal.isInGroup(shape);
	}
	
	public boolean contains(Shape shape) {
		return drawingReal.contains(shape);
	}
	
	public void print() {
		drawingReal.print();
	}
	
	public void removeShape(Shape s) {
		
	}
}
