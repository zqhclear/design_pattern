package designMode.abstractfactory;

public class Client {
	public static void main(String[] args) throws Exception{
		BusinessFactory businessFactory = new BusinessFactory();
		BenzCar benzCar = businessFactory.createBenzCar("benzB");
		businessFactory.createBmwCar("bmw");
		System.out.println(benzCar.getName());
		
		SportFactory sportFactory = new SportFactory();
		sportFactory.createBenzCar("benzS");
		sportFactory.createBmwCar("bmwS");
	}
}
