package designMode.simplefactory;

public class Client {
	public static void main(String[] args){
		Factory factory = new Factory();
		Dog dog = (Dog)factory.getAnimal("dog");
		Cat cat = (Cat)factory.getAnimal("cat");
	}
}
