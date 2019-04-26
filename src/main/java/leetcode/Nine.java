package leetcode;

/**
 * @desc: 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1:
 * 输入: 121
 * 输出: true
 * <p>
 * 示例 2:
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * <p>
 * 示例 3:
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * <p>
 * 进阶:	你能不将整数转为字符串来解决这个问题吗？
 * @author: zhongqionghua
 * @create: 2019/4/25 11:05
 */
public class Nine {

	public static void main(String[] args) {
		System.out.println(isPalindrome(10));

	}

	/**
	 * 我遍历重新再组一个值出来 之后再对比
	 *
	 * @param x
	 * @return
	 */
	public static boolean isPalindrome(int x) {
		if (x % 10 == 0 && x != 0) {
			return false;
		}
		int newNumber = 0;
		//只需要取到中间的位置就可以,比较剩余两边的值
		while (x > newNumber) {
			newNumber = newNumber * 10 + x % 10;
			x = x / 10;
		}
		return x == newNumber || x == newNumber / 10;
	}
}
