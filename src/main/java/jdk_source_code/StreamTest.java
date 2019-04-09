package jdk_source_code;

import com.alibaba.fastjson.JSONObject;

import java.util.stream.Stream;

/**
 * @description: 注释
 * @author: zhongqionghua
 * @Date: 2019/3/19 13:46
 */
public class StreamTest {
	public static void main(String[] args){
		Stream stream  = Stream.of("a", "bc", "def", "ghijk", "lm", "n");
		System.out.println("count():" + stream.count());

		String str = "12,45,78,97,15,10";
		System.out.println(JSONObject.parseArray("[" + str + "]", String.class));
	}
}
