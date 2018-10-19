package designMode.visitor;
/**
 * 抽象访问者：为每一个具体节点都准备了一个访问操作。
 * 这里由于有两个节点（猫，狗），因此，对应就有两个访问操作。
 * eg:人
 * @desc
 * @author zhongqionghua
 * @date 2018年4月13日
 */
public interface Person {
	void feedCat(Cat cat);

	void feedDog(Dog dog);
}
