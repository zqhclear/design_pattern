package designmode.proxy.dynamic_proxy;

public class SubjectImpl implements AbstractSubject {

	@Override
	public void sayCommon() {
		System.out.println("this is a common say!");
	}
}
