package designMode.command;

/**
 * 命令模式是一个高内聚的模式，其定义为：
 * Encapsulate a request as an object,there by letting you parameterize clients with 
 * different requests,queue or log requests,and support undoable operations.
 * （将一个请求封装成一个对象，从而让你使用不同的请求把客户端参数化，对请 求排队或者记录请求日志，
 * 可以提供命令的撤销和恢复功能。）
 * @desc
 * @author zhongqionghua
 * @date 2018年5月2日
 */
public class Client {
	Reciever reciveverImplOne = new ReciveverImplOne();
	Command command = new ConcreteCommandOne(reciveverImplOne);
	Invoke invoke1 = new Invoke();
}
