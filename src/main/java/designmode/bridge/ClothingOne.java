package designmode.bridge;

public class ClothingOne implements Clothing {

	@Override
	public void sayMyClothing(Person person) {
		System.out.println(person.getType() + "穿clothingOne");
	}

}
