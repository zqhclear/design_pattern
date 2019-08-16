package designmode.abstractfactory;

public class BenzSportCar extends BenzCar {
	public BenzSportCar(String car) {
		super();
		this.setName(car);
	}

	@Override
	public void drive() {
		System.out.println(this.getName() + "----BenzSportCar-----------------------");
	}
} 