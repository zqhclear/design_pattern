package designmode.abstractfactory;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/8/15 17:45
 */
public class Car {
	public Car(String name) {
		this.name = name;
	}

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
