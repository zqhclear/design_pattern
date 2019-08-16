package designmode.observer;
/**
 * 抽象被观察者接口
 * 声明了添加、删除、通知观察者方法
 * @desc
 * @author zhongqionghua
 * @date 2018年4月12日
 */
public interface Subject {
	/**
	 * 添加观察者
	 * @param observer
	 */
	void addObserver(Observer observer);

	/**
	 * 删除观察者
	 * @param observer
	 */
	void delObserver(Observer observer);

	/**
	 * 当被观察者变更状态是,需要通过该方法通知所有的已注册的观察者
	 */
	void notifyObserver();

	void doByMySelf();
}
