package designmode.decorator;

/**
 * 下面定义三种装饰，这是第一个，第二个第三个功能依次细化，即装饰者的功能越来越多
 */
public class AbstractDecorator_zero extends AbstractDecorator {

	public AbstractDecorator_zero(Human human) {
		super(human);
	}


	@Override
	public void wearClothes() {
		super.wearClothes();
		goHome();
	}

	@Override
	public void walkToWhere() {
		super.walkToWhere();
		findMap();
	}

	private void goHome() {
		System.out.println("进房子。。");
	}

	private void findMap() {
		System.out.println("书房找找Map。。");
	}
}  