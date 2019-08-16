package designmode.mediator;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/8/16 17:44
 */
public class CurtainDevice extends SmartDevice{

	@Override
	public void operateDevice(String instruction, SmartMediator mediator) {
		//通过传入指令，打开或关闭窗帘
		System.out.println("窗帘已"+instruction);
		//窗帘通过中介者唤醒音乐设备和洗浴设备
		mediator.curtain(instruction);
	}

	@Override
	public void readyState(String instruction) {
		//如果其他设备开启则调用此方法，唤醒窗帘
		System.out.println("窗帘设备准备"+instruction);
	}

}

