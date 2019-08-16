package designmode.decorator;

public class AbstractDecorator_first extends AbstractDecorator {

	public AbstractDecorator_first(Human human) {
		super(human);
	}

	@Override
	public void wearClothes() {
		super.wearClothes();
		goClothespress();
	}

	@Override
	public void walkToWhere() {
		super.walkToWhere();
		findPlaceOnMap();
	}

	private void goClothespress() {
		System.out.println("去衣柜找找看。。");
	}

	private void findPlaceOnMap() {
		System.out.println("在Map上找找。。");
	}
}  