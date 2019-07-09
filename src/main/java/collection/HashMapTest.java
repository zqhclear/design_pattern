package collection;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @Description: 描述
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/31 15:36
 */
public class HashMapTest {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>(20) {{
			put("a", "a_value");
			put("b", "b_value");
		}};
		System.out.println(JSONObject.toJSONString(map));
		map.merge("b", "b_merge_value", (value, newValue) -> value + newValue);
		System.out.println(JSONObject.toJSONString(map));

		map.compute("b", biFunction);
		System.out.println(JSONObject.toJSONString(map));

		map.computeIfAbsent("c", function);
		System.out.println(JSONObject.toJSONString(map));

		map.computeIfAbsent("b", function);
		System.out.println(JSONObject.toJSONString(map));
	}

	private static BiFunction<String, String, String> biFunction = (item1, item2) -> item1 + item2;


	private static Function<String, String> function = item1 -> item1 + "computeIfAbsent";
}
