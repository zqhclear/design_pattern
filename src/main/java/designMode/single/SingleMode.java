package designMode.single;

/**
 * 懒汉式，及程序部署的时候不会主动生成对象，而是等待需要用的时候才会生成
 * 实现：静态内部类会等到需要用到的时候才加载
 * @author zhongqionghua
 * @date 2018年4月2日
 */
public class SingleMode {
	
	private SingleMode(){
	}
	
	private static class Instance {
		public static final SingleMode SINGLE_MODE = new SingleMode();
	}
	
	public static final SingleMode getInstance(){
		return Instance.SINGLE_MODE;
	}
}
