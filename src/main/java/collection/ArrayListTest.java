package collection;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * @Description: ArrayList测试
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/24 16:03
 */
public class ArrayListTest {
	public static void main(String[] args) {
		ArrayList<Integer> intList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));

		ArrayList<Integer> target = new ArrayList<>(Arrays.asList(2, 3, 4));

		ListIterator listIterator = intList.listIterator(4);
		while(listIterator.hasNext()){
			System.out.println(listIterator.next());
		}

//		System.out.println(Collections.indexOfSubList(intList, target));
//		System.out.println(Collections.lastIndexOfSubList(intList, target));

		Collections.rotate(intList, 6);
		System.out.println(intList);

//		intList.add(0, 6);
//		System.out.println(JSONObject.toJSONString(intList));
//
//		intList.remove(3);
//		System.out.println(JSONObject.toJSONString(intList));
//
//		System.out.println(intList.contains(3));
//
//		// 通过Iterator遍历ArrayList
//		for (Iterator iter = intList.iterator(); iter.hasNext(); ) {
//			System.out.println("next is: " + iter.next());
//		}
//
//		//通过ListIterator遍历ArrayList --> 倒序
//		ListIterator listIterator = intList.listIterator();
//		while (listIterator.hasNext()) {
//			System.out.println("ListIterator asc:" + listIterator.next());
//		}
//		//注意，当想使用hasPrevious倒叙输出list时，必须要先next()
//		while (listIterator.hasPrevious()) {
//			System.out.println("ListIterator desc:" + listIterator.previous());
//		}
//
//		// 将ArrayList转换为数组
//		Integer[] arr = (Integer[]) intList.toArray(new Integer[intList.size()]);
//		System.out.println(JSONObject.toJSONString(intList));
//
//		// 清空ArrayList
//		intList.clear();
//		// 判断ArrayList是否为空
//		System.out.println("ArrayList is empty: " + intList.isEmpty());
//
//		Integer[] it = new Integer[]{null, null, null, null};
//		System.out.println(JSONObject.toJSONString(it));



	}
}
