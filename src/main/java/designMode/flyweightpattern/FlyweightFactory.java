package designMode.flyweightpattern;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 享元工厂角色类 享元工厂角色类，必须指出的是，客户端不可以直接将具体享元类实例化，而必须通过一个工厂对象，
 * 利用一个factory()方法得到享元对象。一般而言，享元工厂对象在整个系统中只有一个，因此也可以使用单例模式。
 * 当客户端需要单纯享元对象的时候，需要调用享元工厂的factory()方法，并传入所需的单纯享元对象的内蕴状态， 由工厂方法产生所需要的 享元对象。
 * 
 * @author zhongqionghua
 * @date 2018年4月2日
 */
public class FlyweightFactory {
	private Map<String, Flyweight> files = new HashMap<String, Flyweight>();
	
	/**
	 * 使用单例模式获取工厂类实例
	 * @desc
	 * @author zhongqionghua
	 * @date 2018年4月2日
	 */
	private static class InnerInstance{
		private static final FlyweightFactory INNERINSTANCE = new FlyweightFactory();
	}
	private FlyweightFactory(){
	}
	public static FlyweightFactory getFlyweightFactory(){
		return InnerInstance.INNERINSTANCE;
	}
	

	/**
	 * 单纯享元工厂
	 * @param state
	 * @return
	 */
	public Flyweight factory(String state) {
		//是否已存在该内蕴状态的值
		if (!files.containsKey(state)) {
			System.out.println("create new demo :" + state);
			// 如果对象不存在则创建一个新的Flyweight对象
			Flyweight fly = new ConcreteFlyweight(state);
			// 把这个新的Flyweight对象添加到缓存中
			files.put(state, fly);
			return fly;
		}
		System.out.println("not create new demo");
		return (Flyweight)files.get(state);
	}
	
	 /**
	  * 复合享元工厂方法
	  * @param compositeState
	  * @return
	  */
    public Flyweight factory(List<String> compositeState){
        ConcreteCompositeFlyweight compositeFly = new ConcreteCompositeFlyweight();

        for(String state : compositeState){
            compositeFly.add(state, factory(state));
        }

        return compositeFly;
    }
	
}
