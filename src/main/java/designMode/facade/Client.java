package designMode.facade;

public class Client {
	public static void main(String[] args){
		facadeController facade = new facadeController();
		facade.sayOne();
		facade.sayTwo();
		facade.sayThree();
	}
}
