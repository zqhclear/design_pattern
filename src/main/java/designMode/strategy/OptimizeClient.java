package designMode.strategy;

/**
 * @desc: 优化:可以使用lembda表达式的方式来避免类策略过大而导致类膨胀的问题
 * @author: zhongqionghua
 * @create: 2019/6/27 17:31
 */
public class OptimizeClient {
	private ValidationStrategy validationStrategy;
	public static void main(String[] args){
		OptimizeClient validationStrategy = new OptimizeClient((String s) -> s.matches("[a-z]+]"));
		System.out.println("is string:" + validationStrategy.valite("asdfasdf"));

		OptimizeClient optimizeClient2 = new OptimizeClient((String s) -> s.matches("\\d+"));
		System.out.println("is number:" + optimizeClient2.valite("5465465"));

	}

	public OptimizeClient(ValidationStrategy validationStrategy) {
		this.validationStrategy = validationStrategy;
	}

	public boolean valite(String s){
		return validationStrategy.execute(s);
	}
}

/**
 * 标明是函数式接口
 */
@FunctionalInterface
interface ValidationStrategy{
	boolean execute(String s);
}
