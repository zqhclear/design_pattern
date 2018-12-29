package invoke;

import com.alibaba.fastjson.JSONObject;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @description: 注释
 * @author: zhongqionghua
 * @Date: 2018/12/26 17:32
 */
public class MethodHandleTest2 {

	public static void main(String[] args) throws Throwable {
		Son2 son2 = new Son2();
		son2.thinking("asdfasd");

		Class targetClass = Class.forName("invoke.GrandFather");
		Method[] methods = targetClass.getMethods();
		for (int i = 0; i < methods.length; i++) {
			System.out.println(methods[i].getName());
		}
		System.out.println();
		Method[] methods1 = targetClass.getDeclaredMethods();
		for (int i = 0; i < methods1.length; i++) {
			System.out.println(methods1[i].getName());
		}

	}
}

class GrandFather {
	void thinking(String add) throws Throwable {
		System.out.println("i am grandFather" + add);
	}

	private void privateMethod() {

	}

	protected void protectedMethod() {

	}

	public void publicMethod() {

	}
}

class Father extends GrandFather {
	@Override
	void thinking(String ss) throws Throwable {
		System.out.println("i am father");
	}
}

/**
 * 使用methodHandle方式
 */
class Son extends Father {
	@Override
	void thinking(String ss) throws Throwable {
		// 找到方法返回为void的方法
		MethodType methodType = MethodType.methodType(void.class);
		// 通过
		Field impl_lookup = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
		impl_lookup.setAccessible(true);
		MethodHandles.Lookup lookup = (MethodHandles.Lookup) impl_lookup.get(null);
		MethodHandle methodHandle = lookup.findSpecial(GrandFather.class, "thinking", methodType, GrandFather.class);
		methodHandle.invoke(this);
	}
}

/**
 * 使用reflect方式实现
 */
class Son2 extends Father {
	@Override
	void thinking(String ll) throws Throwable {
		// 找到方法返回为void的方法
		String className = "invoke.GrandFather";
		Class targetClass = Class.forName(className);
		Method method = targetClass.getDeclaredMethod("thinking", String.class);
		method.invoke(targetClass.newInstance(), "asdfasdfafsdf");


//			GrandFather grandFather = new GrandFather();
//			grandFather.thinking();
	}
}

