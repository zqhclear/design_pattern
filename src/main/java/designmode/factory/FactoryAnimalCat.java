package designmode.factory;

public class FactoryAnimalCat implements AbstractFactory {

	@Override
	public Cat getAnimal() {
		return new Cat();
	}
}
