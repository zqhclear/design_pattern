package designmode.interpreter;

/***
 *
 *@Author zhongqionghua
 *@Description:减法表达式实现类
 *@Date: Created in 16:57 2018/4/17
 *@Modified By:
 *
 */
public class MinusOperation extends AbstractNonTerminalExpression {

	public MinusOperation(Expression e1, Expression e2) {
		super(e1, e2);
	}

	/**
	 * 将两个表达式相减
	 *
	 * @param context
	 * @return
	 */
	@Override
	public int interpreter(Context context) {
		return this.e1.interpreter(context) - this.e2.interpreter(context);
	}
}