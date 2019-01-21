package jvm;

import java.io.IOException;

/**
 * @description: 注释
 * @author: zhongqionghua
 * @Date: 2019/1/11 11:26
 */
public class HookTest {
	public static void main(String[] args) throws InterruptedException, IOException {

		System.out.println((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory())/(double)Runtime.getRuntime().maxMemory());


		Runtime.getRuntime().exec("taskmgr");

//		Thread.sleep(5000);
//		Runtime.getRuntime().addShutdownHook(new ShutDownHook());
//		System.exit(0);
	}

	static class ShutDownHook extends Thread {
		@Override
		public void run() {
			System.out.println("close jvm now");
		}
	}
}
