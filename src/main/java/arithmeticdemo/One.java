package arithmeticdemo;

/**
 * m个苹果，n个盘子，多少种方法
 * @author Administrator
 *
 */
public class One {
	static int m = 7; //m>=0
	static int n = 9; //n<=10
	
	public static void main(String[] args){
		int amount = fun(m, n);
		System.out.println("amount:" + amount);
		
	}
	
	private static int fun(int m, int n){
		if(m == 0 || n == 1){	//苹果为0 || 盘子为1 其结果都为1
			return 1;
		}else if(m < n){
			return fun(m, m);
		}else{
			return fun(m, n-1) + fun(m - n, n);
		}
	}
}
