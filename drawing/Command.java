package drawing;

public interface Command {
	public void init(Object args[]) throws Exception;
	public void executer();
	public Command copy();
}
