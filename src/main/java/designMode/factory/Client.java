package designMode.factory;

public class Client {
	public static void main(String[] args){
		FactoryAnimalDog factoryAnimalDog = new FactoryAnimalDog();
		Dog dog = factoryAnimalDog.getAnimal();
		
		FactoryAnimalCat factoryAnimalCat = new FactoryAnimalCat();
		Cat cat = factoryAnimalCat.getAnimal();
	}
}
