package designMode.proxy.static_proxy;

public class ProxySubject implements AbstractSubject {

	private AbstractSubject subjectImpl;
	public ProxySubject(AbstractSubject subject){
		this.subjectImpl = subject;
	}
	public void SayCommon() {
		subjectImpl.SayCommon();
	}
	/**
	 * 补充的方法
	 */
	public void saySpecific(){
		System.out.println("this is specific");
	}

}
