package designMode.observer;
/**
 * 抽象被观察者接口
 * 声明了添加、删除、通知观察者方法
 * @desc
 * @author zhongqionghua
 * @date 2018年4月12日
 */
public interface Subject {
	public void addObserver(Observer observer);
	
	public void delObserver(Observer observer);
	
	public void notifyObserver();
	
	public void doByMySelf();
}
