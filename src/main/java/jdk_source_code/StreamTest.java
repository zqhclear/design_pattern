package jdk_source_code;

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
	}
}
