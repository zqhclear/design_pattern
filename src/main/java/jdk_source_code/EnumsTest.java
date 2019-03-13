package jdk_source_code;

import java.util.Random;

/**
 * @description: 注释
 * @author: zhongqionghua
 * @Date: 2019/2/12 13:59
 */
public class EnumsTest {
	public static void main(String[] args) {
		//返回当前枚举的名字
		System.out.println(EnumTest.FIRST1.name());
		//返回枚举中当前元素的位置，从0开始
		System.out.println(EnumTest.FIRST1.ordinal());
		//对比大小，使用的是ordinal()方法比较
		System.out.println(EnumTest.FIRST1.compareTo(EnumTest.SECOND));

		System.out.println(EnumTest.FIRST1.getDeclaringClass());

		//不能在外部使用构造函数创建实例，
		//EnumTest enumTest = new EnumTest("", 3);


		for (int i = 0; i < 10; i++) {
			//当该对象放在for外层时,则每次结果还是随机的,因为当一个random被调用多次,结果不固定
			Random random = new Random(47);
			System.out.println(random.nextInt(20));
		}
	}
}

enum EnumTest {
	/**
	 * 第一
	 */
	FIRST1("FIRST_ID", 25),
	/**
	 * 第二
	 */
	SECOND("SECOND_ID", 30);
	private String id;

	private int age;

	EnumTest(String id, int age) {
		this.id = id;
		this.age = age;
	}
}

