package jvm.static_classloader;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/5/28 9:37
 */
public class SubClass extends SuperClass {
	static{
		System.out.println("sub class loader");
	}
}
