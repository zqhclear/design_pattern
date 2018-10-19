package designMode.factory;

public class FactoryAnimalCat implements AbstractFactory {

	public Cat getAnimal() {
		return new Cat();
	}
}
