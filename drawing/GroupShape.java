package drawing;

import java.util.ArrayList;
import java.awt.Point;

public class GroupShape implements ComposantShape {
	ArrayList<ComposantShape> composantShape = new ArrayList<ComposantShape>();
	static int id = 0;
	
	private Point center = new Point();
	
	public GroupShape() {
		this.id++;
		center = calculOrigin();
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
		for(int i = 0 ; i<composantShape.size() ; i++) {
			x += composantShape.get(i).getCenter().getX();
			y += composantShape.get(i).getCenter().getY();
		}
		x /= composantShape.size();
		y /= composantShape.size();
		
		Point p = new Point();
		p.setLocation(x, y);
		
		return p;
	}
	
	public void afficher() {
		System.out.println("Group " + id);
		
		for(int i = 0 ; i<composantShape.size() ; i++) {
			composantShape.get(i).afficher();
		}
	}
	
	public void add(ComposantShape c) {
		composantShape.add(c);
	}
	public void remove(ComposantShape c) {
		composantShape.remove(c);
	}
	public ComposantShape getChild(int i) {
		return (ComposantShape) composantShape.get(i);
	}
}