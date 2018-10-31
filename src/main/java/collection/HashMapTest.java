package collection;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 描述
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/31 15:36
 */
public class HashMapTest {
	public static void main(String[] args){
		Map<String, String> map = new HashMap<String, String>(20){{
			put("a", "a_value");
			put("b", "b_value");
		}};
		System.out.println(JSONObject.toJSONString(map));
		map.merge("b", "b_merge_value", (value, newValue) -> value + newValue);
		System.out.println(JSONObject.toJSONString(map));
	}

}
