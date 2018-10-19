package designMode.observer;

/**
 * 具体观察者 one
 * 实现了update方法
 * @desc
 * @author zhongqionghua
 * @date 2018年4月12日
 */
public class ObserverOne implements Observer {

	public void update() {
		System.out.println("update for ObserverOne");
	}

}
