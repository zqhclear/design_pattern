package designMode.proxy.static_proxy;

public class ProxySubject implements AbstractSubject {

	private AbstractSubject subject;
	public ProxySubject(AbstractSubject subject){
		this.subject = subject;
	}
	@Override
	public void SayCommon() {
		subject.SayCommon();
	}
	/**
	 * 补充的方法
	 */
	public void saySpecific(){
		System.out.println("this is specific");
	}

}
