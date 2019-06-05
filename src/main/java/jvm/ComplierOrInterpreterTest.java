package jvm;

/**
 * @desc: jvm解释执行(interpreter)和编译执行(compiler:client/server两种模式)
 * @author: zhongqionghua
 * @create: 2019/5/28 17:28
 */
public class ComplierOrInterpreterTest {
	private static final int NUM = 15000;

	public static void main(String[] args){
		for (int i = 0; i < NUM; i++) {
			calcSum();
		}
	}

	private static long calcSum(){

		long sum = 0;
		for (int i = 0; i < 100; i++) {
			sum += doubleValue(i);
		}
		return sum;
	}

	private static int doubleValue(int i){
		for (int j = 0; j < 100000; j++) {

		}
		return i*2;
	}
}
