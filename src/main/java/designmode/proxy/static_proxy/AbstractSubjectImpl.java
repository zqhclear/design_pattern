package designmode.proxy.static_proxy;

public class AbstractSubjectImpl implements Subject {

	@Override
	public void sayCommon() {
		System.out.println("this is common!");
	}

}
