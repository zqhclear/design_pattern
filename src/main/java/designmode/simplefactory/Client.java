package designmode.simplefactory;

/**
 * @desc: 简单工厂模式
 * 当新增animal子类时,会违背--修改关闭,扩展开放原则
 * @author: zhongqionghua
 * @create: 2019/8/15 16:21
 */
public class Client {
	public static void main(String[] args) {
		Factory factory = new Factory();
		Animal dog = factory.getAnimal(AnimalTypeEnum.DOG);
		dog.show();
		Animal cat = factory.getAnimal(AnimalTypeEnum.CAT);
		cat.show();
	}
}
