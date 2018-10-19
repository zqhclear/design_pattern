package designMode.command;

public class ConcreteCommandTwo extends Command {
	public ConcreteCommandTwo(Reciever re) {
		this.reciever = re;
	}
	public void execute(){
		reciever.doSomeThind();
	}
}
