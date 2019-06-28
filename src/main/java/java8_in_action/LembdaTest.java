package java8_in_action;

/**
 * @desc: lembda表达式测试
 * @author: zhongqionghua
 * @create: 2019/6/27 16:47
 */
public class LembdaTest {
	public static void main(String[] args) {
		test();

		//当有多个函数式接口时,需要指定那个接口
		doSomeThing((TaskTest) () -> System.out.println("不知道执行那个方法?"));

	}


	public static void doSomeThing(Runnable r) {
		r.run();
	}

	public static void doSomeThing(TaskTest taskTest) {
		taskTest.execute();
	}

	private static void test() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				int a = 0;
				System.out.println(a);
			}
		};

		int a = 0;
		Runnable runnable1 = () -> {
			int b = 0;
			System.out.println(a);
		};
	}
}

@FunctionalInterface
interface TaskTest {
	void execute();
}
