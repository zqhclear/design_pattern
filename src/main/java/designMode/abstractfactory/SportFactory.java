package designMode.abstractfactory;

public class SportFactory implements AbstractFactory {

	public BenzCar createBenzCar(String car) throws Exception {
		return new BenzSportCar(car);
	}

	public BmwCar createBmwCar(String car) throws Exception {
		return new BmwSportCar(car);
	}
}
