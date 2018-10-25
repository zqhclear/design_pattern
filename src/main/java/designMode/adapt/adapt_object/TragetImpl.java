package designMode.adapt.adapt_object;
/**
 * 具体目标类，只提供普通功能 
 * @desc
 * @author zhongqionghua
 * @date 2018年5月4日
 */
public class TragetImpl implements Target {

	@Override
	public void doCommon() {
		System.out.println("this is the common type");
	}

}
