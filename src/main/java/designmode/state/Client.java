package designmode.state;

/**
 * @Description: 状态模式：允许对象在其内部状态发生变化时改变其行为，从外部来看，对象似乎改变了其类。
 * 状态模式的重点是状态和依赖状态的行为。大家有用过酒店预订APP吧，在预订酒店时会看到酒店的状态，
 * 有房、满员；满员时你不可预订，只有有房时才能预订。这就是状态模式要模拟的现实场景。
 * <p>
 * 优点：状态模式分离了不同状态的不同行为，避免了逻辑处理时大量解构和行为的可扩展，在不使用状态模式时添加新的状态可
 * 能需要改变整个类以添加新的分支；方便不同状态行为的复用；
 * <p>
 * 缺点：导致较多不同状态类的出现；实现稍显复杂；没有做到完全的开放-关闭原则，当有新的状态加入时，需要修改封装类；
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/18 13:47
 */
public class Client {
	public static void main(String[] args) {
		TVController tvController = new TVController();
		tvController.setOff();
		tvController.prevChanel();
		tvController.nextChanel();

		tvController.setOn();
		tvController.nextChanel();
	}
}
