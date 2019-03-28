package jdk_source_code.function_program;

import java.util.function.Function;

/**
 * @description: Function也是一个函数式编程接口；它代表的含义是“函数”，而函数经常是有输入输出的，
 * 因此它含有一个apply方法，包含一个输入与一个输出；
 * 除apply方法外，它还有compose与andThen及indentity三个方法
 * @author: zhongqionghua
 * @Date: 2019/3/19 11:15
 */
public class FunctionTest {

	public static void main(String[] args){
		System.out.println(functionTest.apply("first"));
		System.out.println(functionTest.andThen(functionTest2).apply("first"));
		System.out.println(functionTest.compose(functionTest2).apply("first"));
	}

	public static Function<String, String> functionTest = item -> "test1--" + item;
	public static Function<String, String> functionTest2 = item -> "test2--" + item;
}
