package thinking_in_java;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @description:十一章:容器
 * @author: zhongqionghua
 * @Date: 2019/2/13 11:50
 */
public class Eleven {
	public static void main(String[] args) {
		initArrayList();
	}

	/**
	 * 使用arrays类简便的构造arrayList
	 */
	public static void initArrayList() {
		Integer[] arr = {1, 2, 3, 4, 6, 9};
		System.out.println(Arrays.asList(arr));
		System.out.println(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6)));
	}

}
