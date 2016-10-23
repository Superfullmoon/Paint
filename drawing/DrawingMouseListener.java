package drawing;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import java.awt.Point;
import java.util.ArrayList;

/**
 * Listener pour g�rer la souris dans la zone de dessin
 */
public class DrawingMouseListener implements MouseMotionListener, MouseListener {
	Drawing drawing;
	Shape currentShape = null;
	double deltaX;
	double deltaY;
	Point depart;
	
	public DrawingMouseListener(Drawing d) {
		drawing = d;
	}
	
	/**
	 * Bouge la forme s�lectionn�e (si une forme est s�lectionn�e)
	 */
	public void mouseDragged(MouseEvent e) {
		if(currentShape != null) {
			deltaX = e.getPoint().getX() - depart.getX();
			deltaY = e.getPoint().getY() - depart.getY();
			depart.setLocation(e.getPoint());
			
			currentShape.displace(deltaX, deltaY);
			drawing.repaint();
		}
	}
	
	/**
	 * S�lectionne la forme sur laquelle l'utilisateur a cliqu�
	 */
	public void mousePressed(MouseEvent e) {
		for(Shape cs : drawing) {
			if(cs.isOn(e.getPoint())) {
				this.currentShape = cs;
				depart = e.getPoint();
			}
		}
	}

	/**
	 * D�s�lectionne la forme
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