package thread.synchronizeddemo;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.awt.*;
import java.util.concurrent.*;
import java.util.function.Function;

/**
 * @Description: synchronized理解
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/29 15:13
 */
public class SynchronizedTest {

	static ThreadFactory namedThreadFactory = new ThreadFactoryBuilder()
			.setNameFormat("demo-synchronizedTest-%d").build();
	static ExecutorService executorService = new ThreadPoolExecutor(6, 10,
			5000, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(), namedThreadFactory);

	public static void main(String[] args) {
		SynchronizedTest test = new SynchronizedTest();
		Target target = new Target();
		for(int i =0 ; i<=5; i++){
			executorService.execute(() -> test.method(target));
		}
	}
	public Function method(Target target){
		synchronized (target) {
			target.sayBegin();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			target.sayEnd();
		}
		return null;
	}
}
