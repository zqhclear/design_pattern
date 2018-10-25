package designMode.proxy.dynamic_proxy;

public class AbstractSubjectImpl implements AbstractSubject {

	@Override
	public void sayCommon() {
		System.out.println("this is a common say!");
	}
}
