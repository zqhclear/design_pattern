package designmode.template;

public class ConcreteTemplateTwo extends AbstractTemplate {

	@Override
	void pourVegetable() {
		System.out.println("下锅的是小龙虾");
	}

	@Override
	void pourSauce() {
		System.out.println("放的是小龙虾酱料");
	}

}
