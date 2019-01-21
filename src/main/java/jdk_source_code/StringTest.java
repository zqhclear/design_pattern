package jdk_source_code;

/**
 * @description: String类测试
 * @author: zhongqionghua
 * @Date: 2019/1/18 14:57
 */
public class StringTest {

	public static void main(String[] args){
		String s = "0123456789";
		char[] chars  = s.toCharArray();
		System.out.println(chars[0]);
		String str = new String(chars, 4, 5);
		System.out.println(str);

		String joinStr = String.join("-", "join", "in", "java");
		System.out.println(joinStr);

	}
}
