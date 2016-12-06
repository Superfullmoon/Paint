package drawing;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;

public class CommandFactory implements Iterable<Command> {
	private HashMap<String, Command> commands;
	private ArrayList<Command> history;
	private int current;
	
	public CommandFactory() {
		this.history = new ArrayList<Command>();
		this.commands = new HashMap<String, Command>();
		this.current = -1;
	}
	
	public Iterator<Command> iterator(){
		return history.iterator();
	}
	
	public void addCommand(String name, Command command) {
		this.commands.put(name, command);
	}
	
	public void executeCommand(String name, Object[] args)  {
		if(this.commands.containsKey(name)) {
			Command command = this.commands.get(name).copy();
			
			try {
				command.init(args);
				this.executer(command);
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void executer(Command command) {
		if(this.history.size()-1 - this.current > 0) {
			for(int i=current+1 ; i<history.size() ; i++) {
				this.history.remove(i);
			}
		}
		this.history.add(command);
		this.current = this.history.size()-1;
		command.executer();
	}
	
	public void display() {
		for (Map.Entry<String, Command> e: this.commands.entrySet()) {
            System.out.println(e.getKey() + " => " + e.getValue());
        }
	}
	
	public void printHistory() {
		System.out.println("Affichage de l'historique");
		for(int i=0 ; i<history.size() ; i++) {
			System.out.println("  " + history.get(i));
		}
		System.out.println("Position courante : " + this.current);
	}
	
	public void undo() {
		if(this.current > -1) {
			this.history.get(current).executer();
			this.current--;
		}
	}
	
	public void redo() {
		if(this.current < this.history.size()-1) {
			this.current++;
			this.history.get(current).executer();
		}
	}
	
	public static CommandFactory init() {
		CommandFactory cf = new CommandFactory();
		
		cf.addCommand("1", new CommandShapeCreation());
		cf.addCommand("2", new CommandShapeAssociate());
		cf.addCommand("3", new CommandShapeClone());
		cf.addCommand("4", new CommandShapeText());
		
		return cf;
	}
}
