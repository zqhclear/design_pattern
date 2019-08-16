package designmode.template;

public class ConcreteTemplateOne extends AbstractTemplate {

	@Override
	void pourVegetable() {
		System.out.println("下锅的是包菜");
		
	}

	@Override
	void pourSauce() {
		System.out.println("放的是包菜酱料");
	}

}
