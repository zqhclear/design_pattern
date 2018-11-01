package collection;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 测试类
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/31 15:23
 */
public class HashMapClient {

	public static void main(String[] args){
//		List list = new ArrayList();
//		List list2 = null;
//		System.out.println(Collections.isEmpty(list));
//		System.out.println(Collections.isEmpty(list2));

//		int n = 10;
//		System.out.println(n |= n >>> 1);
//		System.out.println(n);

		Map map = new HashMap(10);
		map.put("a", null);
		map.put("b", null);
		System.out.println(map);
		map.put(null, "cc");
		map.put(null, "dd");
		map.putIfAbsent("b", "insert");
		System.out.println(map);
		System.out.println(map.containsValue(null));
		System.out.println(map.keySet());


		int n = 10 - 1;
		n |= n >>> 1;
		n |= n >>> 2;
		n |= n >>> 4;
		n |= n >>> 8;
		n |= n >>> 16;
		System.out.println((n < 0) ? 1 : n+1);



	}
}
