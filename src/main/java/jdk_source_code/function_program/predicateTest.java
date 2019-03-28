package jdk_source_code.function_program;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * @description: Predicate为函数式接口，predicate的中文意思是“断定”，即判断的意思，判断某个东西是否满足某种条件；
 * 因此它包含test方法，根据输入值来做逻辑判断，其结果为True或者False。
 * @author: zhongqionghua
 * @Date: 2019/3/19 11:06
 */
public class predicateTest {
	public static void  main(String[] args){
		List<String> strList = Arrays.asList("asdf", "asdfasf", "asdfasdf");
		strList.stream().filter(filterKey).forEach(item -> System.out.println(item));
	}

	private static Predicate<String> filterKey = item -> !"asdf".equals(item);

}
