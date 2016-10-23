package drawing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;

import java.util.ArrayList;

// NE FONCTIONNE PAS

public class AssociateButtonListener implements ActionListener {
	private JButton button;
	private Drawing drawing;
	private boolean end;
	private ArrayList<Shape> association;
	private MouseAdapter l;
	
	public AssociateButtonListener(Drawing d, JButton button) {
		
		this.drawing = d;
		this.button = button;
		this.end = false;
		association = new ArrayList<Shape>();
	}
	
	public void actionPerformed(ActionEvent e) {	
/*	
		if(!end) {
			end = true;
			button.setText("Confirmer");
			
			l = new MouseAdapter() {
				public void mouseClicked(MouseEvent e){
					for(Shape s : drawing) {
						if(s.isOn(e.getPoint()) && !association.contains(s) && !drawing.isInGroup(s)) {
							association.add(s);
							System.out.println("-----------");
							System.out.println(association);
						}
					}
				}
			}; 
			drawing.addMouseListener(l);
			
		} else {
			this.instancier();
		}
		*/
	}
	
	public void instancier() {
		drawing.removeMouseListener(l);
		button.setText("Associate");
		button.addActionListener(new AssociateButtonListener(drawing, button));
		
		if(!association.isEmpty()) {
			ShapeGroup g = new ShapeGroup(association);
			drawing.addShape(g);
			end = false;
		}
	}
}