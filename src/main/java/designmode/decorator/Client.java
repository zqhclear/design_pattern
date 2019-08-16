package designmode.decorator;

/**
 * @desc: 装饰器模式
 * @author: zhongqionghua
 * @create: 2019/8/15 16:21
 */
public class Client {
	public static void main(String[] args) {
		Human person = new Person();
		AbstractDecorator decorator = new AbstractDecorator_two(new AbstractDecorator_first(
				new AbstractDecorator_zero(person)));
		decorator.wearClothes();
		decorator.walkToWhere();
	}
}  