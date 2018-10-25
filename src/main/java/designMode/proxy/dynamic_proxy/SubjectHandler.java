package designMode.proxy.dynamic_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SubjectHandler implements InvocationHandler {
	private AbstractSubject subject;
	
	public SubjectHandler(AbstractSubject subject){
		this.subject = subject;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("begin!!!!! this is a dynamic_proxy!");
		Method myMethod = subject.getClass().getDeclaredMethod(method.getName(), method.getParameterTypes());
		myMethod.setAccessible(true);
		System.out.println("end!!!!!!! this is a dynamic_proxy!");
		return myMethod.invoke(subject, args);
	}
}
