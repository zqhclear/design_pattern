package designMode.observer;

import java.util.Enumeration;
import java.util.Vector;
/**
 * 具体被观察者
 * @desc
 * @author zhongqionghua
 * @date 2018年4月12日
 */
public abstract class AbstractSubject implements Subject {

	/**
	 * 被观察者(被订阅者)需要保存所有的观察者(订阅者)的信息,
	 * 以便在更改的时候通知
	 */
	private Vector<Observer> observerVector = new Vector<Observer>();
	
	@Override
	public void addObserver(Observer observer) {
		observerVector.addElement(observer);
	}

	@Override
	public void delObserver(Observer observer) {
		observerVector.remove(observer);
	}

	@Override
	public void notifyObserver() {
		Enumeration<Observer> elements = observerVector.elements();
		while(elements.hasMoreElements()){
			elements.nextElement().update();
		}
	}

}
