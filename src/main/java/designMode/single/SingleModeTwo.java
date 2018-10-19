package designMode.single;

/**
 * 饿汉式
 * @desc
 * @author zhongqionghua
 * @date 2018年4月2日
 */
public class SingleModeTwo {
	private SingleModeTwo(){}
	
	private static final SingleModeTwo singleModeTwo = new SingleModeTwo();
	
	public static SingleModeTwo getSingle(){
		return singleModeTwo;
	}
}
