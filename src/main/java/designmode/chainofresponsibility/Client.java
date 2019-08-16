package designmode.chainofresponsibility;

/**
 * 责任链模式：filter(过滤器)，handler(拦截器)
 * @author zhongqionghua
 * @date 2018年4月2日
 */
public class Client {
	public static void main(String[] args){
		//组装责任链
        Handler handler1 = new ConcreteHandler();
        Handler handler2 = new ConcreteHandler();
        handler1.setSuccessor(handler2);
        //提交请求
        handler1.handleRequest();
	}
}
