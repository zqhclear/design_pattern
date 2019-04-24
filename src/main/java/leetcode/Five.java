package leetcode;

/**
 * @desc: 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * <p>
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 * @author: zhongqionghua
 * @create: 2019/4/24 15:44
 */
public class Five {

	public static void main(String[] args) {
		System.out.println(longestPalindrome("abc"));
		System.out.println("abc".substring(1, 2));
	}

	/**
	 * 找最大回文串
	 *
	 * @param s
	 * @return
	 */
	public static String longestPalindrome(String s) {
		if (s == null || s.length() < 1) {
			return "";
		}
		int start = 0, end = 0;
		for (int i = 0; i < s.length(); i++) {
			//判断abcdcba这种格式
			int len1 = expandAroundCenter(s, i, i);
			//abba格式
			int len2 = expandAroundCenter(s, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return end - start < 1 ? "" : s.substring(start, end + 1);
	}

	/**
	 * 判断回文串的长度
	 *
	 * @param s
	 * @param left
	 * @param right
	 * @return
	 */
	private static int expandAroundCenter(String s, int left, int right) {
		int L = left, R = right;
		while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
			L--;
			R++;
		}
		return R - L - 1;
	}
}
