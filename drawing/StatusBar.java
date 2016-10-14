package drawing;

import javax.swing.*;

public class StatusBar extends JLabel implements ObserverDrawing {
	Drawing drawing;
	String text;
	
	public StatusBar() {
		drawing.addObserver(this);
		this.update();
	}
	
	public StatusBar(Drawing drawing) {
		this.drawing = drawing;
		drawing.addObserver(this);
		this.update();
	}
	
	public void update() {
		int nbRectangle = this.drawing.countRectangle();
		int nbCircle = this.drawing.countCircle();
		
		this.text = "Circle : " + nbCircle + " | Rectangle : " + nbRectangle;
		this.setText(this.text);
	}
}
