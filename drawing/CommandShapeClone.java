package drawing;

public class CommandShapeClone implements Command {
	Drawing drawing;
	Command command_create;
	Command command_associate;
	Shape shape;
	
	public CommandShapeClone() {
		this.shape = null;
		this.drawing = null;
	}
	
	public CommandShapeClone(CommandShapeClone c) {
		
	}
	
	/**
		Initialise par passage de formes ou par passage des propriétés de la forme
	*/
	public void init(Object[] args) {
		this.drawing = (Drawing) args[0];
		
		//command_associate = new CommandShapeAssociate(drawing, );
		
		if(args[1] instanceof ShapeGroup) {
			ShapeGroup sg = (ShapeGroup) args[1];
			shape = sg.clone();
			drawing.addShape(shape);
		} else if(args[1] instanceof Shape) {
			this.shape = (Shape) args[1];
			shape = shape.clone();
		} else {
			String type = (String) args[1];
			
			Object[] params = new Object[args.length-2];
			for(int i=0 ; i<params.length ; i++) {
				params[i] = args[i+2];
			}
			
			this.shape = Shape.create(type, params);
		}
		
	}
	
	public void executer() {
		boolean attach = false;
		if(drawing.isInGroup(shape)) {
			drawing.removeShape(shape);
		} else {
			drawing.addShape(shape);
		}
	}
	
	public Command copy() {
		Command c = new CommandShapeClone(this);
		return c;
	}
}
