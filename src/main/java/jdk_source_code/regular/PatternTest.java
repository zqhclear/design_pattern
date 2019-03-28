package jdk_source_code.regular;

import com.alibaba.fastjson.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: pattern test
 * @author: zhongqionghua
 * @Date: 2019/3/27 15:13
 */
public class PatternTest {

	// \\d匹配一个数字
	private static Pattern pattern = Pattern.compile("\\d+");

	public static void main(String[] args) {
		String[] strs = pattern.split("我23是34钟5琼6华23.");
		System.out.println(JSONObject.toJSONString(strs));

		//该方法只是创建matcher,未进行匹配操作
		Matcher matcher = pattern.matcher("我23是34钟5琼6华23.");

		System.out.println(matcher.pattern());
		//必须要先使用find()方法才能获取matcher中的数据,具体可看源码
		matcher.find();
		System.out.println(matcher.groupCount());
//		while (matcher.find()) {
//			System.out.println(matcher.group());
//		}
	}
}
