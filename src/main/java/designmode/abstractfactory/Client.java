package designmode.abstractfactory;

/**
 * @desc: 抽象工厂模式
 * @author: zhongqionghua
 * @create: 2019/8/15 16:21
 */
public class Client {
	public static void main(String[] args) throws Exception {
		BusinessFactory businessFactory = new BusinessFactory();
		BenzCar benzCar = businessFactory.createBenzCar("benzB");
		businessFactory.createBmwCar("bmw");
		System.out.println(benzCar.getName());

		SportFactory sportFactory = new SportFactory();
		sportFactory.createBenzCar("benzS");
		sportFactory.createBmwCar("bmwS");
	}
}
