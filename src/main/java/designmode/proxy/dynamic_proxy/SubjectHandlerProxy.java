package designmode.proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;


/**
 * 使用proxy代理弊端:其代理类必须要实现接口,如果没有接口会无法生成代理类,可以替换为cglib模式
 *
 * @author zhongqionghua
 * @desc
 * @date 2018年5月4日
 */
public class SubjectHandlerProxy implements InvocationHandler {
	private AbstractSubject subject;

	public SubjectHandlerProxy(AbstractSubject subject) {
		this.subject = subject;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("begin!!!!! this is a dynamic_proxy!");
//		Method myMethod = subject.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());
//		myMethod.setAccessible(true);
		System.out.println("end!!!!!!! this is a dynamic_proxy!");
		return method.invoke(subject, args);
	}
}
