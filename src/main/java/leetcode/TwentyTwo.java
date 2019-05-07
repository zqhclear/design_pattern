package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc: 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * <p>
 * 例如，给出 n = 3，生成结果为：
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 * @author: zhongqionghua
 * @create: 2019/4/30 11:31
 */
public class TwentyTwo {
	public static void main(String[] args) {
		System.out.println(generateParenthesis2(3));
	}

	/**
	 * 优化:最大的差别就是将字符串改为了StringBuilder,避免重复生成字符串
	 *
	 * @param n
	 * @return
	 */
	public static List<String> generateParenthesis2(int n) {
		List<String> res = new ArrayList<>();
		StringBuilder stringBuilder = new StringBuilder();
		findNext(0, 0, n, stringBuilder, res);
		return res;
	}

	public static void findNext(int leftNum, int rightNum, int n, StringBuilder sb, List<String> res) {
		if (sb.length() == n * 2) {
			res.add(sb.toString());
			return;
		}
		if (leftNum < n) {
			sb.append("(");
			findNext(leftNum + 1, rightNum, n, sb, res);
			//成功生成当次之后,需要将'('给删除,.以便后续操作
			sb.delete(sb.length() - 1, sb.length());
		}
		if (rightNum < n && leftNum > rightNum) {
			sb.append(")");
			findNext(leftNum, rightNum + 1, n, sb, res);
			sb.delete(sb.length() - 1, sb.length());
		}
	}


	/**
	 * 使用递归方式,有点慢
	 * 其就是把'('往最大了放,直到放不下,再给安排')'
	 *
	 * @param n
	 * @return
	 */
	public static List<String> generateParenthesis(int n) {
		List<String> ans = new ArrayList();
		backtrack(ans, "", 0, 0, n);
		return ans;
	}

	public static void backtrack(List<String> ans, String cur, int openNum, int closeNum, int max) {
		if (cur.length() == max * 2) {
			ans.add(cur);
			return;
		}
		if (openNum < max) {
			backtrack(ans, cur + "(", openNum + 1, closeNum, max);
		}
		if (closeNum < openNum) {
			backtrack(ans, cur + ")", openNum, closeNum + 1, max);
		}
	}
}
