package designMode.bridge;

public class Client {
	public static void main(String[] args){
		Person man = new Man();
		Person woman = new Woman();
		Clothing clothingOne = new ClothingOne();
		Clothing clothingTwo = new ClothingTwo();
		clothingOne.sayMyClothing(man);
		clothingOne.sayMyClothing(woman);
		clothingTwo.sayMyClothing(man);
		clothingTwo.sayMyClothing(woman);
	}
}
