package drawing;

import java.awt.Point;

interface ComposantShape {
	Point center = new Point();
	
	void afficher();
	Point getCenter();
	void setCenter(Point p);
	
	void add(ComposantShape c);
	void remove(ComposantShape c);
	ComposantShape getChild(int i);
	
}
