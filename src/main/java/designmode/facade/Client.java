package designmode.facade;

/**
 * @desc: 外观模式
 * @author: zhongqionghua
 * @create: 2019/8/15 16:21
 */
public class Client {
	public static void main(String[] args) {
		facadeController facade = new facadeController();
		facade.sayOne();
		facade.sayTwo();
		facade.sayThree();
	}
}
