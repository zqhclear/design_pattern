package java8_in_action;

import java.util.function.Function;
import java.util.stream.LongStream;

/**
 * @desc: Stream的并行操作
 * @author: zhongqionghua
 * @create: 2019/6/27 14:30
 */
public class StreamParallelTest {

	private static final long RANDOM_NUM = 10000000;

	public static void main(String[] args) {
		System.out.println("avg time:" + measureSumPerf(StreamParallelTest::testParallel, RANDOM_NUM));

		System.out.println(measureSumPerf(StreamParallelTest::sideEffectSum, RANDOM_NUM));
	}


	private static long sideEffectSum(long n) {
		BugDemo bugDemo = new BugDemo();
		//串行方式执行没有线程安全问题
//		LongStream.rangeClosed(1, n).forEach(bugDemo::add);
		//如果使用以下的并行模式的话,多线程访问add方法,会出现线程安全问题
		LongStream.rangeClosed(1, n).parallel().forEach(bugDemo::add);
		return bugDemo.getTotal();
	}

	/**
	 * 使用stream的parallel(并行方式)
	 * 注:使用的longstream避免了自动拆装箱,更省时
	 *
	 * @param n
	 * @return
	 */
	private static long testParallel(long n) {
		return LongStream.rangeClosed(1, n).parallel().reduce(0L, Long::sum);
	}

	private static long measureSumPerf(Function<Long, Long> addr, long n) {
		long fesTest = Long.MAX_VALUE;
		for (int i = 0; i < 10; i++) {
			long startTime = System.currentTimeMillis();
			long sum = addr.apply(n);
			long duration = (System.currentTimeMillis() - startTime);
			System.out.println("Result:" + sum);
			fesTest = Long.min(fesTest, duration);
		}
		return fesTest;
	}

}

class BugDemo {
	private long total;

	public void add(long value) {
		total += value;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}
}
