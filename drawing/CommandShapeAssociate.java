package drawing;

import java.util.ArrayList;

public class CommandShapeAssociate implements Command {
	Drawing drawing;
	ShapeGroup shapeGroup;
	
	public CommandShapeAssociate() {
		this.drawing = null;
		this.shapeGroup = null;
	}
	
	public CommandShapeAssociate(CommandShapeAssociate c) {
		
	}
	
	public void init(Object[] args) {
		this.drawing = (Drawing) args[0];
		
		if(args[1] instanceof ShapeGroup) {
			shapeGroup = (ShapeGroup) args[1];
		} else {
			ArrayList<Shape> shapes = new ArrayList<Shape>();
			for(int i=1 ; i<args.length ; i++) {
				shapes.add((Shape) args[i]);
			}
			this.shapeGroup = new ShapeGroup(this.drawing, shapes);
			drawing.removeShape(shapeGroup);
		}
	}

	/**
	 * Associe ou dissocie deux formes ou deux groupes de formes.
	 * - L'association attache le shapeGroup au drawing.
	 * - La dissociation le détache.
	 * */
	public void executer() {
		if(drawing.contains(shapeGroup)) {
			drawing.removeShape(shapeGroup);
		} else {
			drawing.addShape(shapeGroup);
		}
	}
	
	public Command copy() {
		Command c = new CommandShapeAssociate(this);
		return c;
	}
}
