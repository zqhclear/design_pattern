package designmode.proxy.dynamic_proxy;


/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/8/16 9:58
 */
public class SubjectImpl2 implements AbstractSubject {
	@Override
	public void sayCommon() {
		System.out.println("this is common from impl2");
	}
}
