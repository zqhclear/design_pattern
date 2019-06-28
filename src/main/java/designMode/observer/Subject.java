package designMode.observer;
/**
 * 抽象被观察者接口
 * 声明了添加、删除、通知观察者方法
 * @desc
 * @author zhongqionghua
 * @date 2018年4月12日
 */
public interface Subject {
	void addObserver(Observer observer);
	
	void delObserver(Observer observer);
	
	void notifyObserver();
	
	void doByMySelf();
}
