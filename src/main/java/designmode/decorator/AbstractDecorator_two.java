package designmode.decorator;

public class AbstractDecorator_two extends AbstractDecorator {
	  
    public AbstractDecorator_two(Human human) {
        super(human);  
    }

    @Override
    public void wearClothes() {  
        super.wearClothes();  
        findClothes();  
    }  
  
    @Override  
    public void walkToWhere() {  
        super.walkToWhere();
        findTheTarget();  
    }

    private void findClothes() {
        System.out.println("找到一件D&G。。");
    }

    private void findTheTarget() {
        System.out.println("在Map上找到神秘花园和城堡。。");
    }
}  