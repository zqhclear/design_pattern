/**
 * @description: 注释
 * @author: zhongqionghua
 * @Date: 2019/3/18 10:12
 */
public class Singleton {
	private static Singleton singleton = new Singleton();
	public static Integer value1;
	public static Integer value2 = 0;

	static {
		System.out.println(value1 + "" + value2);
	}

	private Singleton() {
		value1++;
		value2++;
	}

	public static Singleton getInstance() {
		return singleton;
	}

	public static void main(String[] args) {
		System.out.println("Singleton1 value1:" + value1);
		System.out.println("Singleton1 value2:" + value2);

		System.out.println("Singleton2 value1:" + Singleton2.value1);
		System.out.println("Singleton2 value2:" + Singleton2.value2);
	}
}

class Singleton2 {
	public static int value1;
	public static int value2 = 0;
	private static Singleton2 singleton2 = new Singleton2();

	private Singleton2() {
		value1++;
		value2++;
	}

	public static Singleton2 getInstance2() {
		return singleton2;
	}

}
