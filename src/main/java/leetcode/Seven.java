package leetcode;

import com.sun.org.apache.regexp.internal.RE;

/**
 * @desc: 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 示例 1:
 * 输入: 123
 * 输出: 321
 * <p>
 * 示例 2:
 * 输入: -123
 * 输出: -321
 * <p>
 * 示例 3:
 * 输入: 120
 * 输出: 21
 * @author: zhongqionghua
 * @create: 2019/4/24 18:10
 */
public class Seven {

	public static void main(String[] args) {
		//int a = -250;
		int a = 2 >> 30;
		System.out.println(a + "\n" + reverse2(a));
	}


	public static int reverse2(int x) {
		try {
			if (x < 0) {
				StringBuffer stringBuilder = new StringBuffer(Math.abs(x) + "");
				String reverse = stringBuilder.reverse().toString();
				return Integer.parseInt("-" + reverse);
			} else {
				StringBuffer stringBuilder = new StringBuffer(x + "");
				String reverse = stringBuilder.reverse().toString();
				return Integer.parseInt(reverse);
			}
		} catch (Exception e) {
			return 0;
		}

	}


	public static int reverse(int x) {
		int rev = 0;
		while (x != 0) {
			int pop = x % 10;
			x /= 10;
			if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
				return 0;
			}
			if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
				return 0;
			}
			rev = rev * 10 + pop;
		}
		return rev;
	}
}
