package designmode.builder;

public class PersonDirector {
	Builder builder;

	public PersonDirector(Builder builder) {
		this.builder = builder;
	}

	public Person constructPerson() {
		Person person = new Person();
		person.setName(builder.receiveName());
		person.setSex(builder.receiveSex());
		person.setAge(builder.receiveAge());
		return person;
	}
}
