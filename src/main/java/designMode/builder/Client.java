package designMode.builder;

/**
 * 构建器模式
 * 变种Builer模式相比于重叠构造器模式和JavaBeans模式，Builder模式实现的对象更利于使用。
 * <p>
 * 对Builer模式使用方法的总结：
 * （1）、外部类的构造函数私有，且参数为静态内部类;
 * （2）、静态内部类拥有外部类相同的属性;
 * （3）、为每一个属性，写一个方法，返回的是Builer;
 * （4）、最后一个方法是build方法，用于构建一个外部类；
 */
public class Client {
	public static void main(String[] args) {
		PersonDirector personDirector = new PersonDirector(new ManBuilder());
		Person person = personDirector.constructPerson();
		System.out.println(person.getName() + person.getAge() + person.getSex());

		int a = 2;
		for (int i = 0; i < 31; i++) {
			a = a * 2;
			System.out.println(a);
		}
		System.out.println(a);
	}
}
