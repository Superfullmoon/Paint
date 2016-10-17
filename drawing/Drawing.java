package drawing;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

/**
 * JPanel pouvant afficher des objets de type Shape
 */
public class Drawing extends JPanel implements Iterable<ComposantShape> {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<ComposantShape> shapes;
	ArrayList<ObserverDrawing> ObserverDrawings;
	
	public Drawing(){
		super();
		shapes = new ArrayList<ComposantShape>();
		ObserverDrawings = new ArrayList<ObserverDrawing>();
	}
	
	/**
	 * Implémentation de l'interface Iterable<Shape>
	 */
	public Iterator<ComposantShape> iterator(){
		return shapes.iterator();
	}
	
	/**
	 * Ajoute une forme au dessin et redessine
	 */
	public void addShape(ComposantShape s){
		shapes.add(s);
		this.repaint();
		this.notifyObservers();
	}
	
	/** 
	 * Redéfinition de la méthode paintComponent() de JComponent
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(ComposantShape s : shapes){
			s.paint(g);
		}
	}
	
	/**
	 * Enlève toutes les formes et redessine
	 */
	public void clear(){
		shapes.clear();
		this.repaint();
		this.notifyObservers();
	}
	
	//
	public int countRectangle() {
		int count = 0;
		for(int i=0 ; i<shapes.size() ; i++) {
			if(shapes.get(i) instanceof Rectangle) {
				count++;
			}
		}
		return count;
	}
	
	public int countCircle() {
		int count = 0;
		for(int i=0 ; i<shapes.size() ; i++) {
			if(shapes.get(i) instanceof Circle) {
				count++;
			}
		}
		return count;
	}
	
	public void addObserver(ObserverDrawing o) {
		ObserverDrawings.add(o);
	}
	
	public void removeObserver(ObserverDrawing o) {
		ObserverDrawings.remove(ObserverDrawings.indexOf(o));
	}
	private void notifyObservers() {
		for(int i=0 ; i<ObserverDrawings.size() ;i++) {
			ObserverDrawings.get(i).update();
		}
	}
}
