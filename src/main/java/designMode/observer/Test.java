package designMode.observer;

/**
 * 观察者模式
 * 在对象之间定义了一对多的依赖，这样一来，当一个对象改变状态，依赖它的对象会收到通知并自动更新。
 * 其实就是发布订阅模式，发布者发布信息，订阅者获取信息，订阅了就能收到信息，没订阅就收不到信息。
 * @desc
 * @author zhongqionghua
 * @date 2018年4月12日
 */
public class Test {
	public static void main(String[] args){
		Subject subject = new MySubjectImpl();
		subject.addObserver(new ObserverOne());
		subject.addObserver(new ObserverTwo());
		subject.doByMySelf();
	}
}
