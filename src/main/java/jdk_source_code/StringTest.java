package jdk_source_code;

import collection.IdentityHashMapTest;
import com.alibaba.fastjson.JSONObject;
import netscape.javascript.JSObject;

/**
 * @description: String类测试
 * @author: zhongqionghua
 * @Date: 2019/1/18 14:57
 */
public class StringTest {

	public static void main(String[] args) {
		String s = "0123456789";
		char[] chars = s.toCharArray();
		System.out.println(chars[0]);
		String str = new String(chars, 4, 5);
		System.out.println(str);

		String joinStr = String.join("-", "join", "in", "java");
		System.out.println(joinStr);

		String strC = "this is string.";
		System.out.println(strC.codePointAt(5));

		char[] charStr = {'a', 'b', 'c', 'd', 'e', 'f', 'g'};
		System.out.println(String.copyValueOf(charStr, 0 ,5));

		String replaceStr = "ASDFASDFASDF";
		System.out.println(replaceStr.replace('A', 'B'));

		String concatStr = "asdf";
		System.out.println(concatStr.concat("qwe"));

		int a = -4;
		int b = a >> 1;
		int c = a << 1;
		int d = a >>> 1;
		System.out.println("binary-a:"+ Integer.toBinaryString(a) + " || binary:" + Integer.toBinaryString(b));
		System.out.println(a+ ":" + b + ":" + c + ":" + d);

		String codePoint = "我是钟琼华asdfwqerqfasfda";
		System.out.println(codePoint.codePointAt(3));
		System.out.println(codePoint.codePointBefore(3));
		System.out.println(codePoint.codePointCount(0, 4));


		String getchars = "abcdefg";
		char[] getcharss = {'我', '是','钟', '琼', '华'};
		getchars.getChars(0, 4, getcharss ,1);
		System.out.println(getchars + ":" + JSONObject.toJSONString(getcharss));

		System.out.println(getchars.getBytes());

		String indexOf = "我是钟琼华";
		System.out.println(indexOf.indexOf("我是"));
		System.out.println(indexOf.indexOf("我钟"));
		System.out.println(indexOf.substring(1, 3));

		String replaceStr2 = "我是钟琼华我是钟琼华我是钟琼华";


		System.out.println(replaceStr2.replaceAll("琼", "没事"));


		String splitStr = "a_b_c_d_e_f_g";
		System.out.println(JSONObject.toJSONString(splitStr.split("_")));
		System.out.println(JSONObject.toJSONString(splitStr.split("_", 4)));


		String internStr1 = new StringBuilder("计算机").append("软件").toString();
		System.out.println(internStr1.intern() == internStr1);


		String internStr2 = new StringBuilder("ja").append("va1").toString();
		System.out.println(internStr2.intern() == internStr2);
	}
}
