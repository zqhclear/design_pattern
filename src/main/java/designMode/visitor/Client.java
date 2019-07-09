package designMode.visitor;


/**
 * 访问者模式：
 * 封装一些作用于某种数据结构中的各元素的操作，
 * 它可以在不改变这个数据结构的前提下定义作用于这些元素的新的操作。
 * 
 * (有这么一个操作，它是作用于一些元素之上的，而这些元素属于某一个对象结构。
 * 同时这个操作是在不改变各元素类的前提下，在这个前提下定义新操作是访问者模式精髓中的精髓。)
 * 
 * 使用场景： 
 * （1）对象结构比较稳定，但经常需要在此对象结构上定义新的操作。
 * （2）需要对一个对象结构中的对象进行很多不同的且不相关的操作，
 * 	       而需要避免这些操作“污染”这些对象的类，也不希望在增加新操作时修改这些类。
 * @author zhongqionghua
 * @date 2018年4月12日
 */
public class Client {
	public static void main(String[] args){
		Animal dog = new Dog();
		Animal cat = new Cat();
		Home home = new Home();
		home.addAnimal(dog);
		home.addAnimal(cat);
		
		Owner owner = new Owner();
		home.action(owner);
		
		OtherPerson otherPerson = new OtherPerson();
		home.action(otherPerson);

		String s = "asdfasdf";
		final String str = s;
		System.out.println(str);
		s = s.replace("asd", "123");
		System.out.println(s);
		
	}
}
