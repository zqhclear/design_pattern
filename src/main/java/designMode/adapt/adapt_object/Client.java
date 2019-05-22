package designMode.adapt.adapt_object;

/**
 * 适配器模式  （对象适配器,相比于类适配器，更加灵活）
 *  适用场景：
     1、已经存在的类的接口不符合我们的需求；
     2、创建一个可以复用的类，使得该类可以与其他不相关的类或不可预见的类（即那些接口可能不一定兼容的类）协同工作；
     3、在不对每一个都进行子类化以匹配它们的接口的情况下，使用一些已经存在的子类。
 * @desc
 * @author zhongqionghua
 * @date 2018年5月4日
 */
public class Client {
	public static void main(String[] args) {
		// 使用普通功能类
		Target target = new TragetImpl();
		target.doCommon();

		Adapter adapter = new Adapter(new Adaptee());
		adapter.doCommon();
	}
}