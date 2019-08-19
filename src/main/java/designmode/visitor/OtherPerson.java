package designmode.visitor;

/**
 * 具体访问者：客人
 *
 * @author zhongqionghua
 * @desc
 * @date 2018年4月13日
 */
public class OtherPerson implements Person {

	@Override
	public void feedCat(Cat cat) {
		System.out.println("客人喂小猫");
	}

	@Override
	public void feedDog(Dog dog) {
		System.out.println("客人喂小狗");
	}

}
