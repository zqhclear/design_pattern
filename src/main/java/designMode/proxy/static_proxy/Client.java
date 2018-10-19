package designMode.proxy.static_proxy;

/**
 * 静态代理在使用时,需要定义接口或者父类,被代理对象与代理对象一起实现相同的接口或者是继承相同父类.
 * @desc
 * @author zhongqionghua
 * @date 2018年5月4日
 */
public class Client {
	public static void main(String[] args){
		AbstractSubject subject = new AbstractSubjectImpl();
		ProxySubject proxy = new ProxySubject(subject);
		proxy.SayCommon();
		proxy.saySpecific();
	}
}
