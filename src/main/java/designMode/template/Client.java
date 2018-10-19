package designMode.template;

/**
 * 模板方法模式：将具体内容延迟到子类去实现。
 * eg: 定义一个操作中的算法的框架，而将一些步骤延迟到子类中。
 * 使得子类可以不改变一个算法的结构即可重新定义该算法的某些特定步骤。
 * 说的通俗一点，就是为子类设计一个模板以便于子类复用里面的方法。
 * 为了避免子类恶意修改方法的实现细节，一般模板方法模式都会在方法上加final。
 * @desc
 * @author zhongqionghua
 * @date 2018年4月12日
 */
public class Client {
	public static void main(String[] args){
		ConcreteTemplateOne one = new ConcreteTemplateOne();
		one.cookProcess();
		ConcreteTemplateTwo two = new ConcreteTemplateTwo();
		two.cookProcess();
	}

}
