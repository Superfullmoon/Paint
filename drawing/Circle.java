package drawing;

import java.awt.*;

public class Circle extends Shape {
	private Color color;
	
	private double radius;
	
	public Circle(Point origin, double radius, Color color){
		this.origin = origin;
		this.radius = radius;
		this.color = color;
		this.center = origin;
	}
	
	public void paint(Graphics g){
		g.setColor(color);
		g.fillOval((int)(origin.getX()-radius), (int)(origin.getY()-radius), (int)(2*radius), (int)(2*radius));
		g.setColor(Color.BLACK);
		g.drawOval((int)(origin.getX()-radius), (int)(origin.getY()-radius), (int)(2*radius), (int)(2*radius));
	}
	
	public boolean isOn(Point p) {
		return distanceToCenter(p)<radius;		
	}
	
	private double distanceToCenter(Point p){
		return this.origin.distance(p);
	}
	
	public String toString() {
		String chaine = "Circle [\n";
		chaine += "  radius : " + radius;
		chaine += "\n  center : " + center;
		chaine += "\n  origin : " + origin;
		chaine += "\n  color : " + color;
		chaine += "\n]";
		
		return chaine;
	}
	
	public void afficher() {
		System.out.println(toString());
	}

	public void displace(double deltaX, double deltaY) {
		int x = (int) (getOrigin().getX() + deltaX);
		int y = (int) (getOrigin().getY() + deltaY);
		
		setOrigin(new Point(x,y));
	}
}