package jvm.static_classloader;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/8/15 10:34
 */
public abstract class AbstractTest {
	public void refresh(){
		doSomething();
	}

	public void doSomething(){
		System.out.println("abstract");
	}
}
