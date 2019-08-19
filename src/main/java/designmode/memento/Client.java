package designmode.memento;

/**
 * @desc: 备忘录模式:在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态，
 * 以便以后当需要时能将该对象恢复到原先保存的状态。该模式又叫快照模式。
 * <p>
 * 备忘录模式是一种对象行为型模式，其主要优点如下。
 * 提供了一种可以恢复状态的机制。当用户需要时能够比较方便地将数据恢复到某个历史的状态。
 * 实现了内部状态的封装。除了创建它的发起人之外，其他对象都不能够访问这些状态信息。
 * 简化了发起人类。发起人不需要管理和保存其内部状态的各个备份，所有状态信息都保存在备忘录中，
 * 并由管理者进行管理，这符合单一职责原则。
 * <p>
 * 其主要缺点是：资源消耗大。如果要保存的内部状态信息过多或者特别频繁，将会占用比较大的内存资源。
 * @author: zhongqionghua
 * @create: 2019/8/19 10:46
 */
public class Client {
	public static void main(String[] args) {
		Originator or = new Originator();
		Caretaker cr = new Caretaker();
		or.setState("S0");
		System.out.println("初始状态:" + or.getState());
		//保存状态
		cr.setMemento(or.createMemento());
		or.setState("S1");
		System.out.println("新的状态:" + or.getState());
		//恢复状态
		or.restoreMemento(cr.getMemento());
		System.out.println("恢复状态:" + or.getState());
	}
}

/**
 * 备忘录
 */
class Memento {
	private String state;

	public Memento(String state) {
		this.state = state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}
}

/**
 * 发起人
 */
class Originator {
	private String state;

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public Memento createMemento() {
		return new Memento(state);
	}

	public void restoreMemento(Memento m) {
		this.setState(m.getState());
	}
}

/**
 * 管理者
 */
class Caretaker {
	private Memento memento;

	public void setMemento(Memento m) {
		memento = m;
	}

	public Memento getMemento() {
		return memento;
	}
}
