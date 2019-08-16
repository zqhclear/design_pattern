package designmode.visitor;

/**
 * 具体元素角色：
 * eg:猫
 * @desc
 * @author zhongqionghua
 * @date 2018年4月13日
 */
public class Cat extends Animal {

	@Override
	public void say(Person person) {
		person.feedCat(this);
		System.out.println("i am cat,i will say 喵喵喵");
	}

}
