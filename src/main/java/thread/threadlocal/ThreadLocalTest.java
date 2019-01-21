package thread.threadlocal;

import com.alibaba.fastjson.JSONObject;

/**
 * @description: 注释
 * @author: zhongqionghua
 * @Date: 2018/12/29 16:13
 */
public class ThreadLocalTest {

	private static ThreadLocal<Demo> str = new ThreadLocal<>();

	public static void main(String[] args) throws InterruptedException {
		str.set(new Demo("caicai"));
		Thread thread  = Thread.currentThread();
		System.out.println(str.get().toString());
		System.gc();
		Thread.sleep(2000);
		System.out.println(str.get().toString());
		System.out.println("一阶段完成");

		//str.set(new Demo("caicai2"));
		str.remove();
		System.gc();
		Thread.sleep(10000);
		System.out.println(str.get().toString());

	}
}

class Demo {
	private String say;

	public Demo(String say) {
		this.say = say;
	}

	@Override
	public String toString() {
		return "say:" + say;
	}

	@Override
	protected void finalize() throws Throwable {
		System.out.println(say + "对象被回收了");
	}
}
