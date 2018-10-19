package designMode.observer;

public class MySubjectImpl extends AbstractSubject {

	public void doByMySelf() {
		System.out.println("Observer will update!");
		notifyObserver();
	}

}
