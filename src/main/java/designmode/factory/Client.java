package designmode.factory;

/**
 * @desc: 工厂方法模式
 * 和简单工厂的区别就是,没有在factory中使用switch来判断生成的子类,新增animal子类时,
 * 不需要修改原有代码,只需要添加一个子类对应的工厂类就成
 * <p>
 * 但是这个模式当子类有很多的时候,要生成相应的工厂实现类,类爆炸
 * @author: zhongqionghua
 * @create: 2019/8/15 16:21
 */
public class Client {
	public static void main(String[] args) {
		FactoryAnimalDog factoryAnimalDog = new FactoryAnimalDog();
		Animal dog = factoryAnimalDog.getAnimal();
		dog.show();
		FactoryAnimalCat factoryAnimalCat = new FactoryAnimalCat();
		Animal cat = factoryAnimalCat.getAnimal();
		cat.show();
	}
}
