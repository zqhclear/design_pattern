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

	private Vector<Observer> observerVector = new Vector<Observer>();
	
	public void addObserver(Observer observer) {
		observerVector.addElement(observer);
	}

	public void delObserver(Observer observer) {
		observerVector.remove(observer);
	}

	public void notifyObserver() {
		Enumeration<Observer> elements = observerVector.elements();
		while(elements.hasMoreElements()){
			elements.nextElement().update();
		}
	}

}
