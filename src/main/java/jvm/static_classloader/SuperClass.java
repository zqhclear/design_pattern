package jvm.static_classloader;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/5/28 9:36
 */
public class SuperClass {
	static {
		System.out.println("super class loader");
	}

	public static String value = "123";
}
