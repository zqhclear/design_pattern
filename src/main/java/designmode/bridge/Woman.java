package designmode.bridge;

public class Woman extends Person {
	public Woman(){
		setType("女人");
	}

	@Override
	public void dressClothes() {
		getClothing().sayMyClothing(this);
	}
}
