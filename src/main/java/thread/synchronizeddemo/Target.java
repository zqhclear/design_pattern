package thread.synchronizeddemo;

import java.util.concurrent.Executors;

/**
 * @Description:
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/29 15:13
 */
public class Target {
	private String name;

	public void sayBegin() {
		System.out.println(Thread.currentThread() + "begin unlock");
	}
	public void sayEnd() {
		System.out.println(Thread.currentThread() + "end unlock");
	}

	public synchronized void sayLock() {
		System.out.println(Thread.currentThread() + "name is lock");
	}

}
