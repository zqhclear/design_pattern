package designMode.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 结构对象角色：
 * 这个结构对象角色持有一个聚集，并向外界提供add()方法作为对聚集的管理操作。
 * 通过调用这个方法，可以动态地增加一个新的节点。
 * 
 * 静态分派：方法的重载，在编译期就能知道其具体的方法
 * 动态分派：方法的重写，在运行期才能够确定具体的方法
 * 
 * 静态类型，实际类型
 * 
 * 双分派：类似观察者模式，所谓双分派技术就是在选择一个方法的时候，
 * 不仅仅要根据消息接收者（receiver）的运行时区别（Run time type），还要根据参数的运行时区别
 * 
 * @desc
 * @author zhongqionghua
 * @date 2018年4月13日
 */
public class Home {
	private List<Animal> animalList = new ArrayList<Animal>();
	
	public void action(Person person){
		animalList.stream().forEach(animal->
			animal.say(person)
		);
	}
	
	public void addAnimal(Animal animal){
		animalList.add(animal);
	}

}
