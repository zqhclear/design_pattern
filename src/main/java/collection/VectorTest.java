package collection;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @Description: Vector测试类
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/25 18:40
 */
public class VectorTest {

	public static void main(String[] args) {
		Vector vec = new Vector();
		vec.add("1");
		vec.add("2");
		vec.add("3");
		vec.add("4");
		vec.add("5");

		vec.set(0, "100");
		vec.add(2, "300");
		System.out.println("vec:" + vec);

		System.out.println("vec.indexOf(100):" + vec.indexOf("100"));
		System.out.println("vec.lastIndexOf(100):" + vec.lastIndexOf("100"));
		System.out.println("vec.firstElement():" + vec.firstElement());
		System.out.println("vec.elementAt(2):" + vec.elementAt(2));
		System.out.println("vec.lastElement():" + vec.lastElement());

		System.out.println("size:" + vec.size());
		System.out.println("capacity:" + vec.capacity());

		System.out.println("vec 2 to 4:" + vec.subList(1, 4));

		// 通过Enumeration遍历Vector
		Enumeration enu = vec.elements();
		while (enu.hasMoreElements()) {
			System.out.println("nextElement():" + enu.nextElement());
		}

		Vector retainVec = new Vector();
		retainVec.add("100");
		retainVec.add("300");
		// 获取“vec”中包含在“retainVec中的元素”的集合
		System.out.println("vec.retain():" + vec.retainAll(retainVec));
		System.out.println("vec:" + vec);

		// 获取vec对应的String数组
		String[] arr = (String[]) vec.toArray(new String[0]);
		for (String str : arr)
		{
			System.out.println("str:" + str);
		}

		// 清空Vector。clear()和removeAllElements()一样！
		vec.clear();

		// 判断Vector是否为空
		System.out.println("vec.isEmpty():" + vec.isEmpty());
	}
}
