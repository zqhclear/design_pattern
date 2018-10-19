package designMode.bridge;

public class ClothingOne implements Clothing {

	public void sayMyClothing(Person person) {
		System.out.println(person.getType() + "穿clothingOne");
	}

}
