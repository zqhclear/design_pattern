package designmode.mediator;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/8/16 17:45
 */
public abstract class SmartMediator {
	//保留所有设备的引用是为了当接收指令时可以唤醒其他设备的操作
	SmartDevice bd;
	SmartDevice md;
	SmartDevice cd;
	public SmartMediator(SmartDevice bd, SmartDevice md, SmartDevice cd) {
		this.bd = bd;
		this.md = md;
		this.cd = cd;
	}
	public abstract void music(String instruction);
	public abstract void curtain(String instruction);
	public abstract void bath(String instruction);
}
