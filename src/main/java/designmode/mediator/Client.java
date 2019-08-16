package designmode.mediator;

/**
 * @desc: 中介模式(比如微信, qq就是人与人之间沟通的中介, 不通过和这个中介就只能面对面交谈了)
 * 定义一个中介对象来封装一系列对象之间的交互，使原有对象之间的耦合松散，且可以独立地改变它们之间的交互。
 * 中介者模式又叫调停模式，它是迪米特法则的典型应用。
 * <p>
 * 中介者模式是一种对象行为型模式，其主要优点如下。
 * 降低了对象之间的耦合性，使得对象易于独立地被复用。
 * 将对象间的一对多关联转变为一对一的关联，提高系统的灵活性，使得系统易于维护和扩展。
 * <p>
 * 其主要缺点是：当同事类太多时，中介者的职责将很大，它会变得复杂而庞大，以至于系统难以维护,中介者承担过多责任，维护不好会出大事
 *
 * 注:当新增设备时,需要更改中介者,其他不需要改
 * @author: zhongqionghua
 * @create: 2019/8/16 17:29
 */
public class Client {
	public static void main(String[] args) {
		SmartDevice bd = new BathDevice();
		SmartDevice cd = new CurtainDevice();
		SmartDevice md = new MusicDevice();
		//把设备引用都保存在调停者中
		SmartMediator sm = new ConcreteMediator(bd, cd, md);
		//开启窗帘
		cd.operateDevice("open", sm);
		//关闭音乐
		md.operateDevice("close", sm);
	}
}