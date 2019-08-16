package designmode.simplefactory;

public class Factory {
	public Animal getAnimal(AnimalTypeEnum typeEnum) {
		switch (typeEnum) {
			case CAT:
				return new Cat();
			case DOG:
				return new Dog();
			default:
				return null;
		}
	}
}
