package jvm.static_classloader;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/5/28 9:34
 */
public class ClassStaticLoaderTest {

	public static void main(String[] args){
		test1();
//		test2();
	}


	/**
	 * 使用数组的形式,不会初始化类
	 */
	private static void test2(){
		SuperClass[] superClasses = new SuperClass[10];
	}

	/**
	 * 通过子类访问父类的静态属性,只会初始化父类,不会初始化子类
	 */
	private static void test1(){
		System.out.println(SubClass.value);
	}
}
