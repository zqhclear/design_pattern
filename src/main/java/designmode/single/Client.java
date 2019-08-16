package designmode.single;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @desc: 饿汉, 懒汉会发生序列化和反射破坏单例的情况
 * 枚举方式不会,所以首选枚举方式实现单例模式
 * @author: zhongqionghua
 * @create: 2019/8/15 16:24
 */
public class Client {
	public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException, ClassNotFoundException {
//		testSingleReflect();
//		testSingleSingleLazy();

		testSingleEnum();
	}

	/**
	 * 反射对于单例模式的破坏
	 */
	private static void testSingleReflect() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
		SingleHungry singleHungry = SingleHungry.getInstance();
		SingleHungry singleHungry1 = SingleHungry.getInstance();

		Constructor<SingleHungry> constructor = SingleHungry.class.getDeclaredConstructor();
		constructor.setAccessible(true);
		SingleHungry singleHungryReflect = constructor.newInstance();

		System.out.println(singleHungry == singleHungry1);

		System.out.println(singleHungry == singleHungryReflect);
	}

	/**
	 * 序列化/反序列化对单例模式的破坏
	 */
	private static void testSingleSingleLazy() throws IOException, ClassNotFoundException {
		SingleLazy singleLazy = SingleLazy.getInstance();
		singleLazy.setContent("单例序列化");
		System.out.println("序列化前读取其中的内容：" + singleLazy.getContent());
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("SingleLazy.obj"));
		oos.writeObject(singleLazy);
		oos.flush();
		oos.close();

		FileInputStream fis = new FileInputStream("SingleLazy.obj");
		ObjectInputStream ois = new ObjectInputStream(fis);
		SingleLazy s1 = (SingleLazy) ois.readObject();
		ois.close();
		System.out.println(singleLazy + "\n" + s1);
		System.out.println("序列化后读取其中的内容：" + s1.getContent());
		System.out.println("序列化前后两个是否同一个：" + (singleLazy == s1));
	}

	/**
	 * 使用枚举实现单例模式是否会有上面的问题
	 */
	public static void testSingleEnum() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, IOException, ClassNotFoundException {
		SingleEnum singleEnum = SingleEnum.INSTANCE;
//		SingleEnum singleEnum2 = SingleEnum.INSTANCE;
//		Constructor<SingleEnum> constructor = SingleEnum.class.getDeclaredConstructor();
//		constructor.setAccessible(true);
//		SingleEnum singleEnumReflect = constructor.newInstance();
//
//		System.out.println(singleEnum == singleEnum2);
//		System.out.println(singleEnum == singleEnumReflect);

		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("SingleEnum.obj"));
		oos.writeObject(singleEnum);
		oos.flush();
		oos.close();

		FileInputStream fis = new FileInputStream("SingleEnum.obj");
		ObjectInputStream ois = new ObjectInputStream(fis);
		SingleEnum s1 = (SingleEnum) ois.readObject();
		ois.close();
		System.out.println("序列化后读取其中的内容：" + s1.getContent());
		System.out.println("序列化前后两个是否同一个：" + (singleEnum == s1));


	}
}
