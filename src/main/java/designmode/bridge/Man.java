package designmode.bridge;

public class Man extends Person {

	public Man() {
		setType("男");
	}

	@Override
	public void dressClothes() {
		getClothing().sayMyClothing(this);
	}
}
