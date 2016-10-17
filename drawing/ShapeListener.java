package drawing;

import java.awt.event.*;

public class ShapeListener implements MouseListener {	
	Shape shape;
	
	public ShapeListener() {}

	public ShapeListener(Shape s) {
		shape = s;
	}

  public void mouseClicked(MouseEvent event) { 
	System.out.println("Test");
  }

  public void mouseEntered(MouseEvent event) {
  }

  public void mouseExited(MouseEvent event) { }

  public void mousePressed(MouseEvent event) {
	  
  }

  public void mouseReleased(MouseEvent event) { } 
}