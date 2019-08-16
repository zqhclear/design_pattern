package designmode.decorator;

/**
 * 定义装饰者
  */
public abstract class AbstractDecorator implements Human {
	private Human human;

	public AbstractDecorator(Human human) {
		this.human = human;
	}

	@Override
	public void wearClothes() {
		human.wearClothes();
	}

	@Override
	public void walkToWhere() {
		human.walkToWhere();
	}
}  