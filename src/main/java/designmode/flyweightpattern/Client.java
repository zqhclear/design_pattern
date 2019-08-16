package designmode.flyweightpattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 享元模式（Flyweight Pattern）主要用于减少创建对象的数量，
 * 以减少内存占用和提高性能。这种类型的设计模式属于结构型模式，它提供了
 * 减少对象数量从而改善应用所需的对象结构的方式。享元模式尝试重用现有的
 * 同类对象，如果未找到匹配的对象，则创建新对象
 *
 * @author zhongqionghua
 * @date 2018年4月6日
 */
public class Client {
	public static void main(String[] args) {
		//单纯享元模式
		FlyweightFactory factory = FlyweightFactory.getInstance();
		Flyweight fly = factory.factory("a");
		fly.outOfSideOperate("first");

		fly = factory.factory("b");
		fly.outOfSideOperate("second");

		fly = factory.factory("c");
		fly.outOfSideOperate("thrid");


		List<String> compositeState = new ArrayList<String>();
		compositeState.add("a");
		compositeState.add("b");
		compositeState.add("c");
		compositeState.add("a");
		compositeState.add("b");

		//对象是存储在工厂中
		FlyweightFactory flyFactory = FlyweightFactory.getInstance();
		Flyweight compositeFly1 = flyFactory.factory(compositeState);
		System.out.println("first is done");
		Flyweight compositeFly2 = flyFactory.factory(compositeState);
		compositeFly1.outOfSideOperate("Composite Call");

		System.out.println("---------------------------------");
		System.out.println("复合享元模式是否可以共享对象：" + (compositeFly1 == compositeFly2));

		String state = "a";
		Flyweight fly1 = flyFactory.factory(state);
		Flyweight fly2 = flyFactory.factory(state);
		System.out.println("单纯享元模式是否可以共享对象：" + (fly1 == fly2));
	}
}
