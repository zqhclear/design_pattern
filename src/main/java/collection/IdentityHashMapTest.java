package collection;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @description: 注释
 * @author: zhongqionghua
 * @Date: 2019/1/11 15:33
 */
public class IdentityHashMapTest {

	public static void main(String[] args) {
		String ab = new String("asdfasdfas");
		String abCopy = new String("asdfasdfas");
		Map<String, Object> map = new IdentityHashMap<>();
		map.put(ab, "asdfas");
		map.put(abCopy, "4654646");
		System.out.println(map);


		Map<String, Object> map2 = new HashMap<>();
		map2.put(ab, "asdfasdf");
		map2.put(abCopy, "asdfasfd");
		System.out.println(map2);
	}
}
