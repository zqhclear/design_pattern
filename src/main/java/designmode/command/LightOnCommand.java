package designmode.command;

public class LightOnCommand implements Command {

	private LightReceive lightReceive;

	/**
	 * 创建开灯命令的时候，传入具体的灯对象，由灯对象操作自己
	 *
	 * @param lightReceive
	 */
	public LightOnCommand(LightReceive lightReceive) {
		this.lightReceive = lightReceive;
	}

	/**
	 * 具体的灯对象调用自己的开灯方法
	 */
	@Override
	public void execute() {
		lightReceive.lightOn();
	}
}