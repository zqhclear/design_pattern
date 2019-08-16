package designmode.command;

/**
 * 命令模式是一个高内聚的模式，其定义为：
 * Encapsulate a request as an object,there by letting you parameterize clients with
 * different requests,queue or log requests,and support undoable operations.
 * （将一个请求封装成一个对象，从而让你使用不同的请求把客户端参数化，对请 求排队或者记录请求日志，
 * 可以提供命令的撤销和恢复功能。）
 * <p>
 * 优点:
 * 降低系统的耦合度。命令模式能将调用操作的对象与实现该操作的对象解耦。
 * 增加或删除命令非常方便。采用命令模式增加与删除命令不会影响其他类，它满足“开闭原则”，对扩展比较灵活。
 * 可以实现宏命令。命令模式可以与组合模式结合，将多个命令装配成一个组合命令，即宏命令。
 * 方便实现 Undo 和 Redo 操作。命令模式可以与后面介绍的备忘录模式结合，实现命令的撤销与恢复。
 * <p>
 * 缺点：可能产生大量具体命令类。因为计对每一个具体操作都需要设计一个具体命令类，这将增加系统的复杂性。
 *
 *
 * 注:是不是类似于事件监听机制,也是这种命令的发起者和执行分开,添加了一个中间层(listen)来操作
 * @author zhongqionghua
 * @desc
 * @date 2018年5月2日
 */
public class Client {

	public static void main(String[] args) {
		// 创建小爱同学
		XiaoAiInvoke xiaoAi = new XiaoAiInvoke();
		// 创建具体的等对象，相当于具体的命令接受者
		LightReceive light = new LightReceive();
		// 创建了开灯的命令，你就是命令的发起者
		System.out.println("小爱同学帮我把灯开一下！");
		LightOnCommand lightOnCommand = new LightOnCommand(light);
		// 小爱同学接受到了你发出的命令,并执行命令
		xiaoAi.setCommand(lightOnCommand);
		xiaoAi.doCommand();

		System.out.println("-------------------------------------------------");
		System.out.println("小爱同学帮我关一下灯！");
		LightOffCommand lightOffCommand = new LightOffCommand(light);
		xiaoAi.setCommand(lightOffCommand);
		xiaoAi.doCommand();
	}
}
