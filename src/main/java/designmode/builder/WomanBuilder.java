package designmode.builder;

public class WomanBuilder implements Builder {

	@Override
	public String receiveName() {
		return "name:womanName";
	}

	@Override
	public String receiveSex() {
		return "sex:woman";
	}

	@Override
	public String receiveAge() {
		return "age:23";
	}

}
