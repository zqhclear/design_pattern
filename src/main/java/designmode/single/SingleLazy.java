package designmode.single;

import java.io.Serializable;

/**
 * @desc: 懒汉式
 * 静态内部类只有当使用到的时候才会加载(即其中的属性才会被初始化)
 * @author: zhongqionghua
 * @create: 2019/8/15 16:24
 */
public class SingleLazy implements Serializable {

	private static final long serialVersionUID = 3346202547405724885L;

	private SingleLazy() {
	}

	private static class Instance {
		public static final SingleLazy SINGLE_MODE = new SingleLazy();
	}

	public static final SingleLazy getInstance() {
		return SingleLazy.Instance.SINGLE_MODE;
	}

	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
