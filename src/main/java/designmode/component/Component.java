package designmode.component;

/**
 * @desc: 一个抽象构件, 声明一个接口用于访问和管理component的子部件
 * <p>
 * 分为透明式和安全式:
 * 透明式:即在component中就直接声明add,remove方法
 * 安全式:不在component中声明add,remove方法,而是只在composite中才能进行add,remove操作
 * @author: zhongqionghua
 * @create: 2019/8/16 15:18
 */
public abstract class Component {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Component(String name) {
		this.name = name;
	}

	public abstract void add(Component component);

	public abstract void remove(Component component);

	/**
	 * 显示层级结构
	 *
	 * @param level
	 */
	public abstract void display(int level);
}
