package designmode.visitor;

/**
 * 具体访问者：主人
 *
 * @author zhongqionghua
 * @desc
 * @date 2018年4月13日
 */
public class Owner implements Person {

	@Override
	public void feedCat(Cat cat) {
		System.out.println("主人喂小猫");
	}

	@Override
	public void feedDog(Dog dog) {
		System.out.println("主人喂小狗");
	}

}
