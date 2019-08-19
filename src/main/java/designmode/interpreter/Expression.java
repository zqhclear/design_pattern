package designmode.interpreter;

/***
 *
 *@Author zhongqionghua
 *@Description:解释器接口
 *@Date: Created in 16:20 2019/4/17
 *@Modified By:
 *
 */
public interface Expression {
	/**
	 * 一定会有解释方法
	 *
	 * @param context
	 * @return
	 */
	int interpreter(Context context);
}