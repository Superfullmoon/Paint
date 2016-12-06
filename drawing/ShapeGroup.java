package drawing;

import java.util.ArrayList;
import java.util.Iterator;

import java.awt.Point;
import java.awt.Graphics;

public class ShapeGroup extends Shape implements Iterable<Shape> {
	ArrayList<Shape> shapes = new ArrayList<Shape>();
	static int count = 0;
	int id;
	
	private Point center = new Point();
	Drawing drawing;
	
	public ShapeGroup(Drawing d) {
		this.count++;
		this.id = count;
		center = calculOrigin();
		
		this.drawing = d;
		drawing.addShape(this);
	}
	
	public ShapeGroup(Drawing d, ArrayList<Shape> shapes) {
		for(Shape s : shapes) {
			this.shapes.add(s);
		}
		
		this.count++;
		this.id = count;
		center = calculOrigin();
		
		this.drawing = d;
		drawing.addShape(this);
	}
	
	public Iterator<Shape> iterator(){
		return shapes.iterator();
	}
	
	public Point calculOrigin() {
		double x = 0;
		double y = 0;
		for(Shape c : shapes) {
			x += c.getCenter().getX();
			y += c.getCenter().getY();
		}
		x /= shapes.size();
		y /= shapes.size();
		
		Point p = new Point();
		p.setLocation(x, y);
		
		return p;
	}
	
	public void afficher() {
		System.out.println("Group " + id);
		
		for(Shape c : shapes) {
			c.afficher();
		}
	}
	
	public void add(Shape shape) {
		if(!this.contains(shape)) {
			shapes.add(shape);
		}
	}
	
	public void add(ShapeGroup shapeGroup) {
		for(Shape shape : shapeGroup) {
			if(!this.contains(shape)) {
				shapes.add(shape);
			}
		}
	}
	
	public void remove(Shape c) {
		shapes.remove(c);
	}
	public Shape getChild(int i) {
		return (Shape) shapes.get(i);
	}
	
	public void paint(Graphics g) {
		for(Shape cs : shapes) {
			cs.paint(g);
		}
	}
	
	public boolean isOn(Point p) {
		for(Shape it : shapes) {
			if(it.isOn(p)) {
				return true;
			}
		}
		return false;
	}
	
	public void displace(double deltaX, double deltaY) {
		for(Shape cs : shapes) {
			cs.displace(deltaX, deltaY);
		}
	}
	
	public boolean contains(Shape shape) {
		if(shapes.contains(shape)) {
			return true;
		} 
		return false;
	}
	
	public void dissociate() {
		drawing.removeShape(this);
	}
	
	public Shape clone() {
		ArrayList<Shape> sg = new ArrayList<Shape>();
		for(Shape s : shapes) {
			Shape shapeClone = s.clone();
			this.drawing.addShape(shapeClone);
			sg.add(shapeClone);
		}
		
		ShapeGroup group = new ShapeGroup(this.drawing, sg);
		return group;
	}
}
