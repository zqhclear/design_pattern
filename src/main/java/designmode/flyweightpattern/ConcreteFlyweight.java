package designmode.flyweightpattern;

/**
 * 具体享元角色(单纯)
 *
 * @author zhongqionghua
 * @date 2018年4月2日
 */
public class ConcreteFlyweight implements Flyweight {

	private String status;

	/**
	 * 构造函数，内蕴状态作为参数传入
	 *
	 * @param status
	 */
	public ConcreteFlyweight(String status) {
		this.status = status;
	}

	/**
	 * 外蕴状态作为参数传入方法中，改变方法的行为，
	 * 但是并不改变对象的内蕴状态。
	 */
	@Override
	public void outOfSideOperate(String typeStatus) {
		System.out.println("Intrinsic State = " + this.status);
		System.out.println("Extrinsic State = " + typeStatus);
	}
}
