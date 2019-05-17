package leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Stack;

/**
 * @desc: 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * 示例 1:
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 * @author: zhongqionghua
 * @create: 2019/5/7 16:55
 */
public class ThirtyTwo {

	public static void main(String[] args) {
		String str = ")()(()()";
		System.out.println(longestValidParentheses3(str));
	}

	/**
	 * 原理:分别正序和倒叙都排列一次
	 * 正序:当出现')'的次数>'('的次数,则表示当前字符串是不符合的
	 * 倒叙:当出现'('的次数>')'的次数,则表示当前字符串是不符合的
	 * 最后比较两次排序进行计算的字符数量,取max
	 * @param s
	 * @return
	 */
	public static int longestValidParentheses3(String s) {
		char[] chars = s.toCharArray();
		return Math.max(calc(chars, 0, 1, chars.length, '('), calc(chars, chars.length - 1, -1, -1, ')'));
	}

	/**
	 * 计算满足()规则的最大字符串的length
	 *
	 * @param chars
	 * @param i
	 * @param flag
	 * @param end
	 * @param cTem
	 * @return
	 */
	private static int calc(char[] chars, int i, int flag, int end, char cTem) {
		int max = 0, sum = 0, currLen = 0, validLen = 0;
		for (; i != end; i += flag) {
			sum += (chars[i] == cTem ? 1 : -1);
			currLen++;
			if (sum < 0) {
				max = max > validLen ? max : validLen;
				sum = 0;
				currLen = 0;
				validLen = 0;
			} else if (sum == 0) {
				validLen = currLen;
			}
		}
		return max > validLen ? max : validLen;
	}


	/**
	 * [没看]
	 *
	 * dp 方法:
	 * 我们用 dp[i] 表示以 i 结尾的最长有效括号；
	 * 当s[i] 为(,dp[i] 必然等于0,因为不可能组成有效的括号;
	 * 那么s[i] 为 )
	 * 2.1 当 s[i-1] 为 (，那么 dp[i] = dp[i-2] + 2；
	 * 2.2 当 s[i-1] 为 ) 并且 s[i-dp[i-1] - 1] 为 (，那么 dp[i] = dp[i-1] + 2 + dp[i-dp[i-1]-2]；
	 * 时间复杂度：O(n)O(n)
	 *
	 * @param s
	 * @return
	 */
	public static int longestValidParentheses2(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int[] dp = new int[s.length()];
		int res = 0;
		for (int i = 0; i < s.length(); i++) {
			if (i > 0 && s.charAt(i) == ')') {
				if (s.charAt(i - 1) == '(') {
					dp[i] = (i - 2 >= 0 ? dp[i - 2] + 2 : 2);
				} else if (s.charAt(i - 1) == ')' && i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
					dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0);
				}
			}
			res = Math.max(res, dp[i]);
		}
		return res;
	}


	/**
	 * 使用stack栈实现
	 *
	 * @param s
	 * @return
	 */
	public static int longestValidParentheses(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		Stack<Integer> stack = new Stack<>();
		stack.push(-1);
		int res = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else {
				stack.pop();
				if (stack.isEmpty()) {
					stack.push(i);
				} else {
					res = Math.max(res, i - stack.peek());
				}
			}
		}
		return res;
	}
}
