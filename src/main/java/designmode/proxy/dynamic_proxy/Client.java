package designmode.proxy.dynamic_proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理模式:在有些情况下，一个客户不能或者不想直接访问另一个对象，这时需要找一个中介帮忙完成某项任务，
 * 这个中介就是代理对象。例如，购买火车票不一定要去火车站买，可以通过 12306 网站或者去火车票代售点买。
 * 又如找女朋友、找保姆、找工作等都可以通过找中介完成。
 *
 * 动态代理模式
 * 需要定义接口或者父类,被代理对象与代理对象一起实现相同的接口或者是继承相同父类.
 *
 * @author zhongqionghua
 * @desc
 * @date 2018年5月4日
 */
public class Client {
	public static void main(String[] args) {
		testProxy();
		testCglib();
	}


	private static void testProxy(){
		AbstractSubject subject = new SubjectImpl();
		SubjectHandlerProxy handler = new SubjectHandlerProxy(subject);
		AbstractSubject proxy = (AbstractSubject) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{AbstractSubject.class}, handler);
		proxy.sayCommon();

		AbstractSubject subject2 = new SubjectImpl2();
		SubjectHandlerProxy handler2 = new SubjectHandlerProxy(subject2);
		AbstractSubject proxy2 = (AbstractSubject) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{AbstractSubject.class}, handler2);
		proxy2.sayCommon();
	}

	private static void testCglib(){
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(SubjectImpl.class);
		enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
			System.out.println("before method run...");
			Object result = proxy.invokeSuper(obj, args);
			System.out.println("after method run...");
			return result;
		});
		SubjectImpl sample = (SubjectImpl) enhancer.create();
		sample.sayCommon();
	}


	public static void reflect(String[] args) throws Exception {
		TestClass testClass = new TestClass();
		Class<?> test = testClass.getClass();
		Field[] fields = test.getDeclaredFields();
		for (Field field : fields) {    //打印该类的所有属性
			System.out.println("type:" + field.getGenericType());
			if ("java.lang.String".equals(field.getGenericType().getTypeName())) {
				System.out.println(field.getGenericType().getTypeName() + "---->yes");
			}
			Method method = (Method) test.getDeclaredMethod("get" + getMethodName(field.getName()));
			// 调用getter方法获取属性值
			Object val = method.invoke(testClass);
			if (val != null && (val instanceof String)) {
				System.out.println("name:" + field.getName() + "|value:" + val + "::::::" + field.getGenericType());
			}

		}
	}

	private static String getMethodName(String fildName) {
		byte[] items = fildName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}
}
