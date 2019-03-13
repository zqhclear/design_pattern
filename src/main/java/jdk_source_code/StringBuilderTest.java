package jdk_source_code;

/**
 * @description: 注释
 * @author: zhongqionghua
 * @Date: 2019/1/28 18:15
 */
public class StringBuilderTest {
	public static void main(String[] args){
		StringBuilder stringBuilder = new StringBuilder("12345678");
		stringBuilder.setLength(5);
		System.out.println(stringBuilder.toString());
		stringBuilder.append("asdfasd");
		System.out.println(stringBuilder.toString());


		StringBuilder builderAppend = new StringBuilder("asdfa");
		System.out.println(builderAppend.append(true));
		System.out.println(builderAppend.append(20));
		System.out.println(builderAppend.append(-200));
		System.out.println(builderAppend.append(-200L));
	}


}


