package drawing;

public class CommandShapeCreation implements Command {
	Shape shape;
	Drawing drawing;
	
	public CommandShapeCreation() {
		this.shape = null;
		this.drawing = null;
	}
	
	public CommandShapeCreation(CommandShapeCreation c) {
	}
	
	/**
	 * Initialise la commande en fonction des paramètres.
	 * Peut recevoir en paramètre :
	 * - soit une forme existante à supprimer
	 * - soit un ensemble de paramètres nécessaires à la création d'une forme
	 * */
	public void init(Object[] args) {
		this.drawing = (Drawing) args[0];
		
		
		if(args[1] instanceof Shape) {
			this.shape = (Shape) args[1];
		} else {
			String type = (String) args[1];
			
			Object[] params = new Object[args.length-2];
			for(int i=0 ; i<params.length ; i++) {
				params[i] = args[i+2];
			}
			
			this.shape = Shape.create(type, params);
		}
	}
	
	/**
	 * Analyse les arguments et lève des exceptions
	 * 
	public void parser(Object[] args) throws ParserException {
		try {
			
		} catch (ParserException e) {
			
		}
	}
	* */
	
	/**
	 * Exécute l'action à effectuer en fonction de l'état antérieur :
	 * attaché au drawing : retrait
	 * détaché du drawing : ajout
	 */
	public void executer() {
		if(drawing.isInGroup(shape)) {
			drawing.removeShape(shape);
		} else {
			drawing.addShape(shape);
		}
	}
	
	public Shape getShape() {
		return shape;
	}
	
	public Command copy() {
		Command c = new CommandShapeCreation(this);
		return c;
	}
}
