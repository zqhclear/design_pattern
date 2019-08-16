package designmode.prototype;

/**
 * @desc: 原型模式复制
 * 可以直接通过clone()方法
 * 分为深复制和浅复制
 * @author: zhongqionghua
 * @create: 2019/8/15 17:08
 */
public class PrototypeDemo implements Cloneable {

	@Override
	protected PrototypeDemo clone() throws CloneNotSupportedException {
		return (PrototypeDemo)super.clone();
	}
}
