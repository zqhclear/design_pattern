package designmode.builder;

public class ManBuilder implements Builder {

	@Override
	public String receiveName() {
		return "name:manName";
	}

	@Override
	public String receiveSex() {
		return "sex:Man";
	}

	@Override
	public String receiveAge() {
		return "age:23";
	}

}
