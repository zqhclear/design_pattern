package jvm;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/5/27 14:55
 */
public class JconsoleTest {

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(new SyAddRunnable(1, 2)).start();
			new Thread(new SyAddRunnable(2, 1)).start();
		}
	}
}

class SyAddRunnable implements Runnable {

	int a, b;

	public SyAddRunnable(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public void run() {
		synchronized (Integer.valueOf(a)) {
			synchronized (Integer.valueOf(b)) {
				System.out.println(a + b);
			}
		}
	}
}
