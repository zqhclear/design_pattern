package designmode.command;

public class LightOffCommand implements Command{

	private LightReceive lightReceive;

	/**
	 * 创建关灯命令的时候，传入具体的灯对象，由灯对象操作自己
	 * @param lightReceive
	 */
	public LightOffCommand(LightReceive lightReceive) {
		this.lightReceive = lightReceive;
	}

	@Override
	/**
	 * 具体的灯对象调用自己的关灯方法
	 */
	public void execute() {
		lightReceive.lightOff();
	}
}