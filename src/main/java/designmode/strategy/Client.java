package designmode.strategy;

/**
 * 	策略模式:1.能够根据所传递对象的不同而具有不同行为的方法被称为策略设计模式
 * 			2.这类方法包含所要执行的算法中固定不变的部分，而“策略”包含变化的部分。
 * 			策略就是传递进去的参数对象，它包含要执行的代码。
 * 优点： 
 * （1）策略模式提供了管理相关的算法族的办法。策略类的等级结构定义了一个算法或行为族。
 * 恰当使用继承可以把公共的代码移到父类里面，从而避免代码重复。
 * （2）使用策略模式可以避免使用多重条件(if-else)语句。多重条件语句不易维护，
 * 它把采取哪一种算法或采取哪一种行为的逻辑与算法或行为的逻辑混合在一起，统统列在一个多重条件语句里面，
 * 比使用继承的办法还要原始和落后。
 * 缺点：
 * （1）客户端必须知道所有的策略类，并自行决定使用哪一个策略类。这就意味着客户端必须理解这些算法的区别，
 * 以便适时选择恰当的算法类。换言之，策略模式只适用于客户端知道算法或行为的情况。
 * （2）由于策略模式把每个具体的策略实现都单独封装成为类，如果备选的策略很多的话，那么对象的数目就会很可观。
 * 
 * @desc
 * @author zhongqionghua
 * @date 2018年4月3日
 */
public class Client {

	public static void main(String[] args) {
		Strategy planA = new ConcreteStrategyA();
		Strategy planB = new ConcreteStrategyB();
		Strategy planC = new ConcreteStrategyC();

		Context contextA = new Context(planA);
		Context contextB = new Context(planB);
		Context contextC = new Context(planC);
		contextA.contextMethod();
		contextB.contextMethod();
		contextC.contextMethod();
	}
}
