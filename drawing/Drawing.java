package drawing;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;

public abstract class Drawing extends JPanel implements Iterable<Shape> {
	public abstract Shape getShape(int id);
	public abstract void setShape(Shape s1, Shape s2);
	public abstract Iterator<Shape> iterator();
	public abstract void addShape(Shape s);
	public abstract void clear();
	public abstract int countRectangle();
	public abstract int countCircle();
	public abstract void addObserver(ObserverDrawing o);
	public abstract void removeObserver(ObserverDrawing o);
	public abstract boolean isInGroup(Shape shape);
	public abstract boolean contains(Shape shape);
	public abstract void print();
	public abstract void removeShape(Shape s);
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
}
