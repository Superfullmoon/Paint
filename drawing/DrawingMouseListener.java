package drawing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.awt.Point;

/**
 * Listener pour gérer la souris dans la zone de dessin
 */
public class DrawingMouseListener implements MouseMotionListener, MouseListener {
	Drawing drawing;
	Shape currentShape = null;
	double deltaX;
	double deltaY;
	
	public DrawingMouseListener(Drawing d){
		drawing = d;
	}
	
	/**
	 * Bouge la forme sélectionnée (si une forme est sélectionnée)
	 */
	public void mouseDragged(MouseEvent e) {
		if(currentShape != null){
			double x = e.getPoint().getX() - deltaX;
			double y = e.getPoint().getY() - deltaY;
			
			Point origin = new Point();
			origin.setLocation(x,y);
			
			currentShape.setOrigin(origin);			
			drawing.repaint();
		}
	}
	
	/**
	 * Sélectionne la forme sur laquelle l'utilisateur a cliqué
	 */
	public void mousePressed(MouseEvent e) {
		for(Shape s : drawing){
			if(s.isOn(e.getPoint())){
				currentShape = s;
				deltaX = e.getPoint().getX() - currentShape.getOrigin().getX();
				deltaY = e.getPoint().getY() - currentShape.getOrigin().getY();
				
				break;
				
				
			}
		}
	}

	/**
	 * Désélectionne la forme
	 */
	public void mouseReleased(MouseEvent e) {
		currentShape = null;
	}

	public void mouseMoved(MouseEvent e) {
		
	}

	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent e) {

	}

	public void mouseExited(MouseEvent e) {
	}
}
