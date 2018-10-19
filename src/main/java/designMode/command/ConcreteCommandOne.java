package designMode.command;

public class ConcreteCommandOne extends Command {

	public ConcreteCommandOne(Reciever re) {
		this.reciever = re;
	}
	public void execute(){
		reciever.doSomeThind();
	}
}
