package designMode.factory;

public class FactoryAnimalDog implements AbstractFactory {

	public Dog getAnimal() {
		return new Dog();
	}
}
