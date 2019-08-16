package designmode.visitor;

/**
 * 具体元素角色：
 * eg:狗
 * @desc
 * @author zhongqionghua
 * @date 2018年4月13日
 */
public class Dog extends Animal {

	@Override
	public void say(Person person) {
		person.feedDog(this);
		System.out.println("i am dog, i will say 汪汪汪");
	}

}
