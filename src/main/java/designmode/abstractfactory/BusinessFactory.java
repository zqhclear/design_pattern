package designmode.abstractfactory;

public class BusinessFactory implements AbstractFactory {

	@Override
	public BenzCar createBenzCar(String car) throws Exception {
		return new BenzBusinessCar(car);
	}

	@Override
	public BmwCar createBmwCar(String car) throws Exception {
		return new BmwSportCar(car);
	}
}
