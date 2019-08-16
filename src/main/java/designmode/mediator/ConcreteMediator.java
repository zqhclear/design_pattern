package designmode.mediator;

/**
 * @desc: 具体中介者
 * @author: zhongqionghua
 * @create: 2019/8/16 17:45
 */
public class ConcreteMediator extends SmartMediator {

	public ConcreteMediator(SmartDevice bd, SmartDevice cd, SmartDevice md) {
		super(bd, cd, md);
	}

	/**
	 * 音乐被唤醒后，使其他设备进入准备状态
	 * @param instruction
	 */
	@Override
	public void music(String instruction) {
		//调用窗帘的准备方法
		cd.readyState(instruction);
		//调用洗浴设备的准备方法
		bd.readyState(instruction);
	}

	@Override
	public void curtain(String instruction) {
		md.readyState(instruction);
		bd.readyState(instruction);
	}

	@Override
	public void bath(String instruction) {
		cd.readyState(instruction);
		md.readyState(instruction);
	}

}
