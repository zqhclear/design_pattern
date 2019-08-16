package designmode.bridge;

public class ClothingTwo implements Clothing {

	@Override
	public void sayMyClothing(Person person) {
		System.out.println(person.getType() + "传衣服two");
	}

}
