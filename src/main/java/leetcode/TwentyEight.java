package leetcode;

/**
 * @desc: 实现 strStr() 函数。
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * 示例 1:
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 * @author: zhongqionghua
 * @create: 2019/5/5 16:30
 */
public class TwentyEight {
	public static void main(String[] args) {
		String sourceStr = "aaa";
		String targetStr = "aaaa";
		System.out.println(strStr2(sourceStr, targetStr));
	}

	public static int strStr2(String haystack, String needle) {
		if (needle.equals("")) {
			return 0;
		}
		char[] haystackChars = haystack.toCharArray();
		char[] needleChars = needle.toCharArray();
		int max = haystackChars.length - needleChars.length;
		if (max < 0) {
			return -1;
		}
		for (int i = 0; i <= max; i++) {
			if (needleChars[0] != haystackChars[i]) {
				continue;
			}
			int j = 1;
			while (j < needleChars.length) {
				if (haystackChars[i + j] != needleChars[j]) {
					break;
				}
				j++;
			}
			if (j == needleChars.length) {
				return i;
			}
		}
		return -1;
	}


	public static int strStr(String haystack, String needle) {
		if (null == needle) {
			return 0;
		}
		if (haystack == null) {
			return -1;
		}
		char[] resourceChars = haystack.toCharArray();
		char[] targetChars = needle.toCharArray();
		for (int i = 0; i <= resourceChars.length - targetChars.length; i++) {
			if (resourceChars[i] == targetChars[0]) {
				for (int k = 1; k < targetChars.length && i + k < resourceChars.length; k++) {
					if (resourceChars[i + k] != targetChars[k]) {
						return -1;
					}
				}
				return i;
			}
		}
		return -1;
	}

}
