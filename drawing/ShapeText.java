package drawing;

import java.awt.Graphics;
import java.awt.Point;

public class ShapeText extends ShapeProperties {
	Shape shape;
	String text;
	
	public ShapeText(Shape shape, String text) throws Exception {
		if(shape instanceof ShapeGroup) {
			throw new Exception("Decoration d'un groupe");
		}
	
		this.shape = shape;
		this.text = text;
	}

	public void displace(double deltaX, double deltaY) {
		shape.displace(deltaX, deltaY);
	}
	public void paint(Graphics g) {
		shape.paint(g);
	}
	public boolean isOn(Point p){
		return shape.isOn(p);
	}
	
	public Shape clone(){
		return shape;
	}
	public void afficher() {
		shape.afficher();
		System.out.println(" " + text);
	}
}
