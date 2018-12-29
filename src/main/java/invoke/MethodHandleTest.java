package invoke;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * @description: java.lang.invoke包测试
 * 功能类似于反射,但是反射是重量级的,其包含的信息比MethodHandle多很多,
 * 并且反射reflect是在java api层面,且只适用于java
 * MethodHandle是基于字节码,可以适用于各种文件编译成的字节码(非只适用于java)
 * @author: zhongqionghua
 * @Date: 2018/12/26 16:37
 */
public class MethodHandleTest {

	public static void main(String[] args) throws Throwable {
		for (int i = 0; i < 5; i++) {
			Object targetObj = System.currentTimeMillis() % 2 == 0 ? System.out : new Demo();
			getObjectMethodHandleMethod(targetObj).invokeExact("see you");
		}
	}


	public static MethodHandle getObjectMethodHandleMethod(Object receiver) throws NoSuchMethodException, IllegalAccessException {
		//
		MethodType methodType = MethodType.methodType(void.class, String.class);

		return lookup().findVirtual(receiver.getClass(), "println", methodType).bindTo(receiver);

	}


	static class Demo {
		public void println(String str) {
			System.out.println("demo say " + str);
		}
	}
}
