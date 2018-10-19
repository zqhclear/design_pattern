package designMode.command;

public class Invoke {
	private Command command;
	
	public void setCommand(Command command){
		this.command = command;
	}
	
	public void action(){
		command.execute();
	}

}
