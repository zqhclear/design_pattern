package designmode.proxy.static_proxy;

public class ProxySubject implements Subject {

	private Subject subject;

	public ProxySubject(Subject subject) {
		this.subject = subject;
	}

	@Override
	public void sayCommon() {
		subject.sayCommon();
	}

	/**
	 * 补充的方法
	 */
	public void saySpecific() {
		System.out.println("this is specific");
	}

}
