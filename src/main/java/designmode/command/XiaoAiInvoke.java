package designmode.command;

/**
 * @desc: 小爱同学:将light和command(开关命令)给解耦
 * @author: zhongqionghua
 * @create: 2019/8/16 16:46
 */
public class XiaoAiInvoke {

	private Command command;

	/**
	 * 设置具体的命令
	 *
	 * @param command
	 */
	public void setCommand(Command command) {
		this.command = command;
	}

	/**
	 * 执行命令
	 */
	public void doCommand() {
		command.execute();
	}
}
