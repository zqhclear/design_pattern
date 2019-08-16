package designmode.abstractfactory;

public class SportFactory implements AbstractFactory {

	@Override
	public BenzCar createBenzCar(String car) throws Exception {
		return new BenzSportCar(car);
	}

	@Override
	public BmwCar createBmwCar(String car) throws Exception {
		return new BmwSportCar(car);
	}
}
