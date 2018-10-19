package designMode.abstractfactory;

public class BusinessFactory implements AbstractFactory {

	public BenzCar createBenzCar(String car) throws Exception {
		return new BenzBusinessCar(car);
	}

	public BmwCar createBmwCar(String car) throws Exception {
		return new BmwSportCar(car);
	}
}
