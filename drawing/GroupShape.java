package drawing;

import java.util.ArrayList;
import java.util.Iterator;
import java.awt.Point;
import java.awt.Graphics;

public class GroupShape implements ComposantShape, Iterable<ComposantShape> {
	ArrayList<ComposantShape> composantShape = new ArrayList<ComposantShape>();
	static int id = 0;
	
	private Point center = new Point();
	Drawing drawing;
	
	public GroupShape(Drawing d) {
		this.id++;
		center = calculOrigin();
		
		this.drawing = d;
		drawing.addShape(this);
	}
	
	public Point getCenter() {
		return center;
	}
	
	public void setCenter(Point p) {
		center = p;
	}
	
	public Point calculOrigin() {
		double x = 0;
		double y = 0;
		for(ComposantShape c : composantShape) {
			x += c.getCenter().getX();
			y += c.getCenter().getY();
		}
		x /= composantShape.size();
		y /= composantShape.size();
		
		Point p = new Point();
		p.setLocation(x, y);
		
		return p;
	}
	
	public void displace() {
		// Problème si un composant est présent dans deux groupes
		
		for (ComposantShape c : composantShape){
			
		}
	}
	
	public void afficher() {
		System.out.println("Group " + id);
		
		for(ComposantShape c : composantShape) {
			c.afficher();
		}
	}
	
	public Iterator<ComposantShape> iterator() {
		return composantShape.iterator();
	}
	
	public void add(ComposantShape c) {
		boolean exist = false;
		for(ComposantShape it : composantShape) {
			if(c == it) {
				exist = true;
			}
		}
		
		if(exist) {
			System.out.println("Shape exists already in this group.");
		} else {
			composantShape.add(c);
		}
	}
	public void remove(ComposantShape c) {
		composantShape.remove(c);
	}
	public ComposantShape getChild(int i) {
		return (ComposantShape) composantShape.get(i);
	}
	
	public void paint(Graphics g) {
		for(ComposantShape cs : composantShape) {
			cs.paint(g);
		}
	}
	
	public boolean isOn(Point p) {
		for(ComposantShape it : composantShape) {
			if(it.isOn(p)) {
				return true;
			}
		}
		return false;
	}
	
	public void displace(double deltaX, double deltaY) {
		for(ComposantShape cs : composantShape) {
			cs.displace(deltaX, deltaY);
		}
	}
}