package designMode.adapt.adapt_class;
/**
 * 适配器模式：类适配器
 * @desc
 * @author zhongqionghua
 * @date 2018年5月4日
 */
public class Client {
	public static void main(String[] args){
		TragetImpl tragetImpl = new TragetImpl();
		tragetImpl.doCommon();
		
		AdaptTragetee adaptTragetOne = new AdaptTragetee();
		adaptTragetOne.adaptMethod();
	}
}
