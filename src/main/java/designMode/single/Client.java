package designMode.single;

public class Client {
	public static void main(String[] args){
		SingleMode singleMode = SingleMode.getInstance();
		System.out.println(singleMode);
	}
}
