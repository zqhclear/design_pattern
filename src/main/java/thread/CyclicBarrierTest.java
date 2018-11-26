package thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.function.Function;

/**
 * @Description: 同步屏障:是停掉这个线程得的所有活动,和这个线程执行什么东西无关
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/29 15:13
 */
public class CyclicBarrierTest {

	static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

	static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
			.setNameFormat("demo-CyclicBarrierTest-%d").build();
	static ExecutorService executorService = new ThreadPoolExecutor(6, 10,
			5000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(), namedThreadFactory);

	public static void main(String[] args) {
//		for (int i = 0; i <= 5; i++) {
//			executorService.execute(new ThreadTest());
//		}
//		System.out.println("先走了");


		// 当同一个线程重复执行cyclicBarrier.await();的时候,会发生什么
		new Thread(() -> doSomething()).start();
		System.out.println("方法执行完毕");
	}

	private static Function doSomething(){
		System.out.println("线程呀");
		for (int i = 0; i < 5; i++) {
			try {
				System.out.println("第" + i + "次");
				cyclicBarrier.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	static class ThreadTest implements Runnable {
		@Override
		public synchronized void run() {
			try {
				cyclicBarrier.await();
				System.out.println(Thread.currentThread().getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				e.printStackTrace();
			}
		}
	}
}
