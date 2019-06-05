package jdk_source_code.function_program;

import java.util.function.Consumer;

/**
 * @description: Consumer是一个函数式编程接口； 顾名思义，Consumer的意思就是消费，即针对某个东西我们来使用它，
 * 因此它包含有一个有输入而无输出的accept接口方法,
 * 除accept方法，它还包含有andThen这个方法；
 * <p>
 * 会先执行本consumer的accept()方法,然后如果有addThen的话,在执行addThen的accept()
 * @author: zhongqionghua
 * @Date: 2019/3/19 11:15
 */
public class ConsumerTest {
	public static void main(String[] args) {
		Consumer f = System.out::println;
		Consumer f2 = n -> {
			System.out.println(n + "-F2");
			System.out.println("asdfasdf");
		};

		//执行完F后再执行F2的Accept方法
		f.andThen(f2).accept("test");

		//连续执行F的Accept方法
//		f.andThen(f).andThen(f2).andThen(f).accept("test1");
	}
}
