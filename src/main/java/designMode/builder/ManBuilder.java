package designMode.builder;

public class ManBuilder implements Builder {

	@Override
	public String recieveName() {
		return "name:manName";
	}

	@Override
	public String recieveSex() {
		return "sex:Man";
	}

	@Override
	public String recieveAge() {
		return "age:23";
	}

}
