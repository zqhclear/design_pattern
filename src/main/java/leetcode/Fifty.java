package leetcode;

import java.math.BigDecimal;

/**
 * @desc: 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 * <p>
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 * <p>
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 说明:
 * <p>
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。
 * @author: zhongqionghua
 * @create: 2019/6/5 16:38
 */
public class Fifty {
	public static void main(String[] args) {
		System.out.println(myPow(2.10000, 3));
	}


	public static double myPow(double x, int n) {
		double ans = pow2(x, Math.abs(n));
		return n >= 0 ? ans : 1 / ans;
	}

	/**
	 * 递归
	 *
	 * @param x
	 * @param n
	 * @return
	 */
	public static double pow2(double x, int n) {
		if (n == 0) {
			return 1;
		}
		if (n == 1){
			return x;
		}
		double temp = pow2(x, n / 2);
		temp *= temp;
		//如果是偶数,则运算temp *= temp刚好,如果是奇数,则还需要*x
		if (n % 2 != 0){
			temp *= x;
		}
		return temp;

	}


	/**
	 * 迭代方法
	 *
	 * @param x
	 * @param n
	 * @return
	 */
	public static double pow(double x, int n) {
		double ans = 1;
		double base = x;
		while (n != 0) {
			if (n % 2 != 0) {
				ans *= base;
			}
			base *= base;
			n = n / 2;
		}
		return ans;

	}
}
