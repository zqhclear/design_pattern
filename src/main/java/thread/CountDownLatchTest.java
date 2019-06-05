package thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * countDownLatch 测试类,当使用这个时候,主线程通过.await()方法阻塞,
 * 	但是次要线程(调用countDown()方法的线程)是会执行完毕的
 * cycleBarrier: 主线程和次要线程都会被阻塞
 * @author: zhongqionghua
 * @Date: 2019/1/14 10:45
 */
public class CountDownLatchTest {
	public static void main(String[] args) throws InterruptedException {

//		System.identityHashCode(new Object());

		CountDownLatch countDownLatch = new CountDownLatch(5);

		for (int i = 0; i < 3; i++) {
			new Thread(new ThreadTest(countDownLatch)).start();
		}
		System.out.println(countDownLatch.getCount());
		countDownLatch.await();


		List list = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(0));
			String s = new String();
		}

		String username = new String();
	}

}

class ThreadTest implements Runnable {

	private CountDownLatch countDownLatch;

	public ThreadTest(CountDownLatch countDownLatch) {
		this.countDownLatch = countDownLatch;
	}

	@Override
	public void run() {
		countDownLatch.countDown();
		System.out.println(countDownLatch.getCount());
	}
}
