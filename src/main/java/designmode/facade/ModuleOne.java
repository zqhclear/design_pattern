package designmode.facade;

public class ModuleOne {
	public void methodOne(){
		System.out.println("this is a module one");
	}
	
	/**
	 * 子系统内调用的方法，不在client门面中
	 */
	public void methodTwo(){
		System.out.println("内部方法two");
	}
	
	public void sayOne(){
		System.out.println("内部方法one");
	}
}
