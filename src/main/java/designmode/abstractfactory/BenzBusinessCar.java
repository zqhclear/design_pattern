package designmode.abstractfactory;

public class BenzBusinessCar extends BenzCar {
	public BenzBusinessCar(String car) {
		super();
		this.setName(car);
	}

	@Override
	public void drive() {
		System.out.println(this.getName() + "----BenzBusinessCar-----------------------");
	}
}  