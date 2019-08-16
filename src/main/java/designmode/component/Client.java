package designmode.component;

/**
 * @desc: 组合模式
 * 组合模式允许你将对象组合成树形结构来表现”部分-整体“的层次结构，使得客户以一致的方式处理单个对象以及对象的组合。
 * 组合模式实现的最关键的地方是——简单对象和复合对象必须实现相同的接口。这就是组合模式能够将组合对象和简单对象进行一致处理的原因。
 *
 * 组合部件（Component）：它是一个抽象角色，为要组合的对象提供统一的接口。
 * 叶子（Leaf）：在组合中表示子节点对象，叶子节点不能有子节点。
 * 合成部件（Composite）：定义有枝节点的行为，用来存储部件，实现在Component接口中的有关操作，如增加（add）和删除（Remove）。
 *
 *
 * 组合模式的优缺点
 * 优点：
 * 组合模式使得客户端代码可以一致地处理对象和对象容器，无需关系处理的单个对象，还是组合的对象容器。
 * 将”客户代码与复杂的对象容器结构“解耦。
 * 可以更容易地往组合对象中加入新的构件。
 * 缺点： 使得设计更加复杂。客户端需要花更多时间理清类之间的层次关系。（这个是几乎所有设计模式所面临的问题）。
 * <p>
 * 注意的问题：
 * 有时候系统需要遍历一个树枝结构的子构件很多次，这时候可以考虑把遍历子构件的结构存储在父构件里面作为缓存。
 * 客户端尽量不要直接调用树叶类中的方法（在我上面实现就是这样的，创建的是一个树枝的具体对象;），而是借用其父类
 * （Graphics）的多态性完成调用，这样可以增加代码的复用性。
 * <p>
 * 在以下情况下应该考虑使用组合模式：
 * 当想表达对象的部分-整体的层次结构时。
 * 希望用户忽略组合对象与单个对象的不同，用户将统一地使用组合结构中的所有对象时。
 * .NET 中Winform 中的空间类型大多用到了该种设计模式。另， 《设计模式》一书中提倡：相对于安全性，我们比较强调透明性。
 * 对于第一种方式中叶子节点内不需要的方法可以使用空处理或者异常报告的方式来解决。
 * @author: zhongqionghua
 * @create: 2019/8/16 15:13
 */
public class Client {
	public static void main(String[] args){
		testLucency();
	}

	public static void testLucency(){
		// 生成树根，并为其增加两个叶子节点
		Component root = new Composite("Root");
		root.add(new Leaf("Leaf A in Root"));
		root.add(new Leaf("Leaf B in Root"));
		// 为根增加两个枝节点
		Component branchX = new Composite("Branch X in Root");
		Component branchY = new Composite("Branch Y in Root");
		root.add(branchX);
		root.add(branchY);
		// 为BranchX增加页节点
		branchX.add(new Leaf("Leaf A in Branch X"));
		// 为BranchX增加枝节点
		Component branchZ = new Composite("Branch Z in Branch X");
		branchX.add(branchZ);
		// 为BranchY增加叶节点
		branchY.add(new Leaf("Leaf in Branch Y"));
		// 为BranchZ增加叶节点
		branchZ.add(new Leaf("Leaf in Branch Z"));
		// 显示树
		root.display(1);
	}

}
