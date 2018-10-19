package designMode.bridge;

public class ClothingTwo implements Clothing {

	public void sayMyClothing(Person person) {
		System.out.println(person.getType() + "传衣服two");
	}

}
