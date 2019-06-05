package jvm;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/5/28 14:19
 */
public class GCTest {
	public static void main(String[] args) throws InterruptedException {
		{
			byte[] bytes = new byte[64 * 1024 * 1024];
			bytes = null;
		}
		System.gc();
		Thread.sleep(10000);
	}
}
