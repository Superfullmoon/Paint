package drawing;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

interface ComposantShape {
	Point center = new Point();
	Point origin = new Point();
	
	void afficher();
	Point getCenter();
	void setCenter(Point p);
	
	void paint(Graphics g);
	void displace(double deltaX, double deltaY);
	boolean isOn(Point p);
	
	void add(ComposantShape c);
	void remove(ComposantShape c);
	ComposantShape getChild(int i);
	
}
