package designmode.factory;

public class FactoryAnimalDog implements AbstractFactory {

	@Override
	public Dog getAnimal() {
		return new Dog();
	}
}
