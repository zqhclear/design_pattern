package designMode.adapt.adapt_object;

/**
 * 已存在的、具有特殊功能、但不符合我们既有的标准接口的类  
 * @desc
 * @author zhongqionghua
 * @date 2018年5月4日
 */
public class Adaptee{
	public void doSpeMethod() {
		System.out.println("this is not common method");
	}
}
