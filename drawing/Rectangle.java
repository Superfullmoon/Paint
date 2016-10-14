package drawing;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Rectangle extends Shape {
	
	int width;
	int height;
	Color color;
	
	public Rectangle(Point origin, int width, int height, Color color){
		this.origin = origin;
		this.width = width;
		this.height = height;
		this.color = color;
		
		double x = origin.getX() + width/2;
		double y = origin.getY() + height/2;
		center = new Point();
		center.setLocation(x, y);
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	public Point getCenter() {
		return center;
	}
	
	public boolean isOn(Point p) {
		return(p.x > origin.x && p.x < origin.x+width && p.y > origin.y && p.y < origin.y+height);
	}

	public void paint(Graphics g) {
		g.setColor(color);
		g.fillRect(origin.x, origin.y, width, height);
		g.setColor(Color.BLACK);
		g.drawRect(origin.x, origin.y, width, height);
	}
	
	public String toString() {
		String chaine = "[\n";
		chaine += "  width : " + width;
		chaine += "\n  height : " + height;
		chaine += "\n  center : " + center;
		chaine += "\n  origin : " + origin;
		chaine += "\n  color : " + color;
		chaine += "\n]";
		
		return chaine;
	}
	
	public void afficher() {
		System.out.println(toString());
	}
}
