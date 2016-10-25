package drawing;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

/**
 * JPanel pouvant afficher des objets de type Shape
 */
public class Drawing extends JPanel implements  Iterable<Shape> {

	private static final long serialVersionUID = 1L;
	
	private ArrayList<Shape> shapes;
	ArrayList<ObserverDrawing> ObserverDrawings;
	
	public Drawing(){
		super();
		shapes = new ArrayList<Shape>();
		ObserverDrawings = new ArrayList<ObserverDrawing>();
	}
	
	/**
	 * Implémentation de l'interface Iterable<Shape>
	 */
	public Iterator<Shape> iterator(){
		return shapes.iterator();
	}
	
	/**
	 * Ajoute une forme au dessin et redessine
	 */
	public void addShape(Shape s){
		shapes.add(s);
		this.repaint();
		this.notifyObservers();
	}
	
	/** 
	 * Redéfinition de la méthode paintComponent() de JComponent
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(Shape s : shapes){
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
	
	public int countRectangle() {
		int count = 0;
		for(Shape s : shapes) {
			if(s instanceof Rectangle) {
				count++;
			}
		}
		return count;
	}
	
	public int countCircle() {
		int count = 0;
		for(Shape s : shapes) {
			if(s instanceof Circle) {
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
	
	public void print() {
		for(Shape s : shapes) {
			s.afficher();
			System.out.println("------");
		}
	}
	
	public void removeShape(Shape s) {
		shapes.remove(s);
	}
}