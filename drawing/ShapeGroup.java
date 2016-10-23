package drawing;

import java.util.ArrayList;
import java.util.Iterator;

import java.awt.Point;
import java.awt.Graphics;

public class ShapeGroup extends Shape  {
	ArrayList<Shape> shapes = new ArrayList<Shape>();
	static int id = 0;
	
	private Point center = new Point();
	Drawing drawing;
	
	public ShapeGroup(Drawing d) {
		this.id++;
		center = calculOrigin();
		
		this.drawing = d;
		drawing.addShape(this);
	}
	
	public ShapeGroup(ArrayList<Shape> shapes) {
		for(Shape s : shapes) {
			this.shapes.add(s);
		}
	}
	
	public Iterator<Shape> iterator() {
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
	
	public void add(Shape c) {
		boolean exist = false;
		for(Shape it : shapes) {
			if(c == it) {
				exist = true;
			}
		}
		
		if(exist) {
			System.out.println("Shape exists already in this group.");
		} else {
			shapes.add(c);
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
}