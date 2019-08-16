package designmode.single;

/**
 * @desc: 饿汉式
 * 	当系统启动的时候, 如果用到了SingleHungry类就会创建单例
 * @author: zhongqionghua
 * @create: 2019/8/15 16:21
 */
public class SingleHungry {

	private SingleHungry() {
	}

	private static SingleHungry singleHungry = new SingleHungry();

	public static SingleHungry getInstance() {
		return singleHungry;
	}
}
