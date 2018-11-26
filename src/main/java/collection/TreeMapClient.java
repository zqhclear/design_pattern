package collection;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Description:
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/29 15:13
 */
public class TreeMapClient {
	public static void main(String[] args){
		Map map = new TreeMap();
		//map.put(null, "a");
		map.put("b", null);
		//map.put(null, "c");
		map.put("d", null);
		System.out.println(map);

		System.out.println(((TreeMap) map).descendingKeySet());
		System.out.println(((TreeMap) map).descendingMap());

		saySomeThings((Object)"sdaf");

	}
	private static void saySomeThings(String a){
		System.out.print(a + "String");
	}
	private static void saySomeThings(Object a){
		System.out.print(a + "Object");
	}
}
