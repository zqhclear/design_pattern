package designmode.bridge;

/**
 * @desc: 桥接模式
 * 将抽象与实现分离，使它们可以独立变化。它是用组合关系代替继承关系来实现，从而降低了抽象和实现这两个可变维度的耦合度。
 * <p>
 * 在现实生活中，某些类具有两个或多个维度的变化，如图形既可按形状分，又可按颜色分。如何设计类似于 Photoshop 这样的软件，
 * 能画不同形状和不同颜色的图形呢？如果用继承方式，m 种形状和 n 种颜色的图形就有 m×n 种，不但对应的子类很多，而且扩展困难。
 * @author: zhongqionghua
 * @create: 2019/8/15 16:21
 */
public class Client {
	public static void main(String[] args) {
		Person man = new Man();
		Person woman = new Woman();
		Clothing clothingOne = new ClothingOne();
		Clothing clothingTwo = new ClothingTwo();
		clothingOne.sayMyClothing(man);
		clothingOne.sayMyClothing(woman);
		clothingTwo.sayMyClothing(man);
		clothingTwo.sayMyClothing(woman);
	}
}
