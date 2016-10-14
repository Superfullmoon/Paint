package drawing;

import java.awt.Graphics;
import java.awt.Point;

/**
 * Classe abstraite de type forme dessinable.
 */
public abstract class Shape implements ComposantShape {
	
	protected Point origin;
	protected Point center;
	
	public Point getCenter()
	{
		return center;
	}
	
	public void setCenter(Point p)
	{
		center = p;
	}
	
	public Point getOrigin()
	{
		return origin;
	}
	
	public void setOrigin(Point p)
	{
		origin = p;
	}
	
	public abstract void afficher();
	
	/**
	 * dessine la forme sur un Graphics
	 */
	public abstract void paint(Graphics g);
	
	/**
	 * renvoie true si la forme occupe sur le point donné
	 */
	public abstract boolean isOn(Point p);
	
	
	
	public void add(ComposantShape c) {
		throw new UnsupportedOperationException();
	}
	public void remove(ComposantShape c) {
		throw new UnsupportedOperationException();
	}
	public ComposantShape getChild(int i) {
		throw new UnsupportedOperationException();
	}
}
