package leetcode;

/**
 * @desc: 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 示例 1:
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 示例 2:
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 说明:
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。本题中，如果除法结果溢出，则返回 231 − 1。
 * @author: zhongqionghua
 * @create: 2019/5/5 17:16
 */
public class TwentyNine {
	public static void main(String[] args) {
		System.out.println(2 << 2);
		System.out.println(divide(31, 3));
	}

	public static int divide(int dividend, int divisor) {
		if (dividend == 0) {
			return 0;
		}
		if (dividend == Integer.MIN_VALUE && divisor == -1) {
			return Integer.MAX_VALUE;
		}
		long t = Math.abs((long) dividend);
		long d = Math.abs((long) divisor);
		int result = 0;
		for (int i = 31; i >= 0; i--) {
			//找出足够大的数2^n*divisor
			if ((t >> i) >= d) {
				//将结果加上2^n
				result += 1 << i;
				//将被除数减去2^n*divisor
				t -= d << i;
			}
		}
		//返回前需要判断正负:异或运算判断其首位是否为1
		return (dividend ^ divisor) < 0 ? -result : result;
	}
}
