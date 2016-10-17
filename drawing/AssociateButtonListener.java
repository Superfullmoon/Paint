package drawing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.*;

public class AssociateButtonListener implements ActionListener {
	Drawing drawing;
	
	public AssociateButtonListener(Drawing d) {
		drawing = d;
	}
	
	public void actionPerformed(ActionEvent e) {		
		for(ComposantShape s : drawing) {
			System.out.println(s);
		}
	}
	
	
}