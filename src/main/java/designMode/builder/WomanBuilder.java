package designMode.builder;

public class WomanBuilder implements Builder {

	public String recieveName() {
		return "name:womanName";
	}

	public String recieveSex() {
		return "sex:woman";
	}

	public String recieveAge() {
		return "age:23";
	}

}
