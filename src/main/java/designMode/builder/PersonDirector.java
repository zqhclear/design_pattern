package designMode.builder;

public class PersonDirector {
	Builder builder;

	public PersonDirector(Builder builder) {
		this.builder = builder;
	}

	public Person constructPerson() {
		Person person = new Person();
		person.setName(builder.recieveName());
		person.setSex(builder.recieveSex());
		person.setAge(builder.recieveAge());
		return person;
	}
}
