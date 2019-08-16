package designmode.flyweightpattern;

/**
 * 享元抽象类
 *
 * @author zhongqionghua
 * @desc
 * @date 2018年4月2日
 */
public interface Flyweight {

	/**
	 * 外蕴状态
	 *
	 * @param typeStatus
	 */
	void outOfSideOperate(String typeStatus);
}
