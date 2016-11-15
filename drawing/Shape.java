package drawing;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.Color;

/**
 * Classe abstraite de type forme dessinable.
 */
public abstract class Shape {
	protected Point center = new Point();
	protected Point origin = new Point();
	
	public Point getCenter() {
		return center;
	}
	
	public void setCenter(Point p) {
		center = p;
	}
	
	public Point getOrigin() {
		return origin;
	}
	
	public void setOrigin(Point p) {
		origin = p;
	}

	public abstract void afficher();
	public abstract void displace(double deltaX, double deltaY);
	
	/**
	 * dessine la forme sur un Graphics
	 */
	public abstract void paint(Graphics g);
	
	/**
	 * renvoie true si la forme occupe sur le point donné
	 */
	public abstract boolean isOn(Point p);
	
	public abstract Shape clone();
	
	/** 
	 * Création des formes grâce à l'introspection
	 *
	*/
	public static Shape create(String type, Object args[]) {
		Shape shape = null;
		
		if(type == "rectangle") {
			Point origin = (Point) args[0];
			int width = (int) args[1];
			int height = (int) args[2];
			Color color = (Color) args[3];
			
			shape = new Rectangle(origin, width, height, color);
		} else if(type == "circle") {
			Point origin = (Point) args[0];
			double radius = (double) args[1];
			Color color = (Color) args[2];
			
			shape = new Circle(origin, radius, color);
		}
		
		return shape;
	}
}






