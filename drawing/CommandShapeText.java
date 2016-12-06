package drawing;

public class CommandShapeText implements Command {
	Shape shape;
	Shape decorate;
	String text;
	Drawing drawing;
	
	public CommandShapeText() {

	}
	
	public CommandShapeText(CommandShapeText c) {
	}
	
	public void init(Object[] args) throws Exception {
		this.drawing = (Drawing) args[0];
		this.shape = (Shape) args[1];
		this.text = (String) args[2];
		
		System.out.println(Paint.toIdentityString(args[1]));
		
		try{
			decorate = new ShapeText(shape, text);
			System.out.println(Paint.toIdentityString(decorate));
		} catch(Exception e) {
			System.out.println(e.getMessage());
			throw new Exception("Commande non initialisee");
		}
	}
	
	public void executer() {
		if(drawing.contains(shape)) {
			drawing.setShape(shape, decorate);
		} else {
			drawing.setShape(decorate, shape);
		}
	}
	
	public Command copy() {
		Command c = new CommandShapeText(this);
		return c;
	}
}
