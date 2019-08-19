package designmode.interpreter;

/***
 *
 *@Author zhongqionghua
 *@Description:抽象非终结符表达式
 *@Date: Created in 16:22 2018/4/17
 *@Modified By:
 *
 */
public abstract class AbstractNonTerminalExpression implements Expression {
	Expression e1, e2;

	public AbstractNonTerminalExpression(Expression e1, Expression e2) {

		this.e1 = e1;
		this.e2 = e2;
	}
}