package designMode.strategy;

public class Context {
	private Strategy strategy;
	public Context(Strategy strategy){
		this.strategy = strategy;
	}
	
	/**
	 * 策略方法
	 */
	public void contextMethod(){
		strategy.strategyMethod();
	}
}
