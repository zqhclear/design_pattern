package thread;

/**
 * @description:
 * countDownLatch 测试类,当使用这个时候,主线程通过.await()方法阻塞,
 * 	但是次要线程(调用countDown()方法的线程)是会执行完毕的
 * cycleBarrier: 主线程和次要线程都会被阻塞
 * @author: zhongqionghua
 * @Date: 2019/1/14 10:45
 */
public class CountDownLatchTest {
	public static void main(String[] args){
		System.identityHashCode(new Object());
	}
}
