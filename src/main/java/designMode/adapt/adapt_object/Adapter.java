package designMode.adapt.adapt_object;

/**
 * 适配器类，继承了被适配类，同时实现标准接口  
 * @desc
 * @author zhongqionghua
 * @date 2018年5月4日
 */
public class Adapter implements Target {
	private Adaptee adaptee;

	public Adapter(Adaptee targetee) {
		this.adaptee = targetee;
	}
	@Override
	public void doCommon() {
		adaptee.doSpeMethod();
	}
}