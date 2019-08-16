package designmode.proxy.dynamic_proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;


/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/8/16 10:23
 */
public class SubjectHandlerCglib implements InvocationHandler {

	private AbstractSubject abstractSubject;

	public SubjectHandlerCglib(AbstractSubject abstractSubject) {
		this.abstractSubject = abstractSubject;
	}

	@Override
	public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
		return null;
	}
}
