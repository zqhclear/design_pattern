package collection;

import java.util.Map;
import java.util.WeakHashMap;

/**
 * @Description:
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/29 15:13
 */
public class WeakHashMapTest {
	public static void main(String[] args){
		Map map = new WeakHashMap();
		map.put(null, "b");
		map.put(null, "a");
		map.put("c", null);
		map.put("d", null);
		map.put("e", "e");
		System.out.println(map);

		System.out.println("first:" + map.size());
		try{
			Thread.sleep(10000);
		}catch (InterruptedException e) {

		}
		System.out.println("last:" + map.size());
	}
}
