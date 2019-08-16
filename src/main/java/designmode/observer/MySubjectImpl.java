package designmode.observer;

public class MySubjectImpl extends AbstractSubject {

	@Override
	public void doByMySelf() {
		System.out.println("Observer will update!");
		notifyObserver();
	}

}
