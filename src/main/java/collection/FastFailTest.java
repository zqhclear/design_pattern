package collection;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author skywang
 * @Description: 描述
 * ava集合中Fast-Fail的测试程序。
 * <p>
 * fast-fail事件产生的条件：当多个线程对Collection进行操作时，若其中某一个线程通过iterator去遍历集合时，该集合的内容被其他线程所改变；则会抛出ConcurrentModificationException异常。
 * fast-fail解决办法：通过util.concurrent集合包下的相应类去处理，则不会产生fast-fail事件。
 * <p>
 * 本例中，分别测试ArrayList和CopyOnWriteArrayList这两种情况。ArrayList会产生fast-fail事件，而CopyOnWriteArrayList不会产生fast-fail事件。
 * (01) 使用ArrayList时，会产生fast-fail事件，抛出ConcurrentModificationException异常；定义如下：
 * private static List<String> list = new ArrayList<String>();
 * (02) 使用时CopyOnWriteArrayList，不会产生fast-fail事件；定义如下：
 * private static List<String> list = new CopyOnWriteArrayList<String>();
 * @Author:zhongqionghua
 * @CreateDate:2018/10/25 11:24
 */

public class FastFailTest {

	/**
	 * arrayList：当多个线程同时操作list集合时，可能会发生fail-fast异常，具体可看arrayList的iterator()方法的
	 */
	//private static List<Integer> list = new ArrayList<>();
	/**
	 * 不会产生fail-fast异常
	 */
	private static List<Integer> list = new CopyOnWriteArrayList();
	/**
	 * threadFactory
	 */
	private static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
			.setNameFormat("demo-FastFailTest-ThreadPool-%d").build();
	/**
	 * 初始化线程池
	 */
	private static ExecutorService executorService = new ThreadPoolExecutor(10,15,
			10,TimeUnit.SECONDS, new LinkedBlockingDeque<>(), namedThreadFactory);

	public static void main(String[] args) {
		//同时启动两个线程操作list
		executorService.execute(new ListModifyFirst());
		executorService.execute(new ListModifyTwo());
	}

	/**
	 * 使用iterator方式输出list
	 */
	private static void printAll() {
		Integer value = null;
		Iterator iterator = list.iterator();
		while (iterator.hasNext()) {
			value = (Integer) iterator.next();
			System.out.print(value + "',' ");
		}
	}

	/**
	 * 线程1操作
	 */
	public static class ListModifyFirst implements Runnable {
		@Override
		public void run() {
			int i = 0;
			while (i < 5) {
				list.add(i++);
				printAll();
			}
		}
	}

	/**
	 * 线程2操作
	 */
	public static class ListModifyTwo implements Runnable {
		@Override
		public void run() {
			int i = 10;
			while (i < 15) {
				list.add(i++);
				printAll();
			}
		}
	}
	
}
