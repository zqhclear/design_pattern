package jvm.static_classloader;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/8/15 10:35
 */
public class TestImpl extends AbstractTest {

	public static void main(String[] args) {
		TestImpl test = new TestImpl();
		test.refresh();
	}

	@Override
	public void refresh() {
		super.refresh();
	}

	@Override
	public void doSomething() {
		System.out.println("impl");
	}
}
