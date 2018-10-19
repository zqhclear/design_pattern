package designMode.visitor;

/**
 * 具体访问者：客人
 * @desc
 * @author zhongqionghua
 * @date 2018年4月13日
 */
public class OtherPerson implements Person {

	public void feedCat(Cat cat) {
		System.out.println("客人喂小猫");		
	}

	public void feedDog(Dog dog) {
		System.out.println("客人喂小狗");
	}

}
