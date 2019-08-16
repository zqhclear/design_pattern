package designmode.mediator;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/8/16 17:44
 */
public class MusicDevice extends SmartDevice {

	@Override
	public void operateDevice(String instruction, SmartMediator mediator) {
		System.out.println("音乐设备已" + instruction);
		mediator.music(instruction);
	}

	@Override
	public void readyState(String instruction) {
		System.out.println("音乐设备准备" + instruction);
	}

}
