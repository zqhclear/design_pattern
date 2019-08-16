package designmode.adapt.adapt_object;

/**
 * 适配器类，继承了被适配类，同时实现标准接口  
 * @desc
 * @author zhongqionghua
 * @date 2018年5月4日
 */
public class Adapter implements SourceClass {
	private Target target;

	public Adapter(Target targetee) {
		this.target = targetee;
	}

	@Override
	public void doSpeMethod() {
		target.doCommon();
	}
}