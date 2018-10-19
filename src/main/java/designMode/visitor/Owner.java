package designMode.visitor;

/**
 * 具体访问者：主人
 * @desc
 * @author zhongqionghua
 * @date 2018年4月13日
 */
public class Owner implements Person {

	public void feedCat(Cat cat) {
		System.out.println("主人喂小猫");
	}

	public void feedDog(Dog dog) {
		System.out.println("主人喂小狗");		
	}

}
