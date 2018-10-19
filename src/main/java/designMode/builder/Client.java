package designMode.builder;

public class Client {
	public static void main(String[] args) {
		PersonDirector personDirector = new PersonDirector(new ManBuilder());
		Person person = personDirector.constructPerson();
		System.out.println(person.getName()+person.getAge()+person.getSex());
		
		int a=2;
		for(int i=0;i<31;i++){
			a=a*2;
			System.out.println(a);
		}
		System.out.println(a);
	}
}
