package drawing;

public interface Command {
	public void init(Object args[]);
	public void executer();
	public Command copy();
}
