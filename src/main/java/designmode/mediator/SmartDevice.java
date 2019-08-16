package designmode.mediator;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/8/16 17:43
 */
public abstract class SmartDevice {
	/**
	 * 相关设备打开之后 使其进入准备状态
	 * @param instruction
	 */
	public abstract void readyState(String instruction);

	/**
	 * 操作该设备
	 * @param instruction
	 * @param mediator
	 */
	public abstract void operateDevice(String instruction, SmartMediator mediator);
}
