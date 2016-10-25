package drawing;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;

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
}