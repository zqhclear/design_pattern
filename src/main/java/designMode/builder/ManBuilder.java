package designMode.builder;

public class ManBuilder implements Builder {

	public String recieveName() {
		return "name:manName";
	}

	public String recieveSex() {
		return "sex:Man";
	}

	public String recieveAge() {
		return "age:23";
	}

}
