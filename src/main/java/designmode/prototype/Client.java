package designmode.prototype;

/**
 * @desc: 原型模式
 * <p>
 * 用一个已经创建的实例作为原型，通过复制该原型对象来创建一个和原型相同或相似的新对象。
 * 在这里，原型实例指定了要创建的对象的种类。用这种方式创建对象非常高效，根本无须知道对象创建的细节。
 * 例如，Windows 操作系统的安装通常较耗时，如果复制就快了很多。
 * <p>
 * java类提供了clone()方法,可以使用这个方式轻松的实现复制
 * Java 中的 Object 类提供了浅克隆的 clone() 方法，
 * 具体原型类只要实现 Cloneable 接口就可实现对象的浅克隆，这里的 Cloneable 接口就是抽象原型类。其代码如下：
 * @author: zhongqionghua
 * @create: 2019/8/15 17:04
 */
public class Client {
	public static void main(String[] args) throws CloneNotSupportedException {
		A a = new A(new B("你猜猜看呀"));
		//自带的clone()是浅拷贝的-即其值还是存在同一个地址,并没有另外创建地址存放
		A a2 = a.clone();
		System.out.println(a == a2);
		System.out.println(a.getContent() == a2.getContent());
	}

}

class A implements Cloneable {
	private B content;

	public A(B content) {
		this.content = content;
	}

	public B getContent() {
		return content;
	}

	public void setContent(B content) {
		this.content = content;
	}

	@Override
	protected A clone() throws CloneNotSupportedException {
		return (A) super.clone();
	}
}

class B {
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public B(String content) {
		this.content = content;
	}
}
