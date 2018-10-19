package designMode.simplefactory;

public class Factory {
	public Animal getAnimal(String animal){
		if("dog".equals(animal)){
			return new Dog();
		}else if("cat".equals(animal)){
			return new Cat();
		}
		return null;
	}
}
