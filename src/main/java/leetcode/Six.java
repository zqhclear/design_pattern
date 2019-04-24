package leetcode;

/**
 * @desc: 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
 * L   C   I   R
 * E T O E S I I G
 * E   D   H   N
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"
 * <p>
 * 示例 1:
 * 输入: s = "LEETCODEISHIRING", numRows = 3
 * 输出: "LCIRETOESIIGEDHN"
 * @author: zhongqionghua
 * @create: 2019/4/24 17:04
 */
public class Six {

	public static void main(String[] args) {
		String str = "LEETCODEISHIRING";
		System.out.println("before:" + str + "\nafter: " + convert(str, 4));
	}

	/**
	 * 将元素分为n组,每组元素个数为(2*numRows -2)
	 *
	 * @param s
	 * @param numRows
	 * @return
	 */
	public static String convert(String s, int numRows) {
		if (numRows == 1) {
			return s;
		}

		StringBuilder ret = new StringBuilder();
		int n = s.length();
		//间隔(即每组元素的个数)
		int cycleLen = 2 * numRows - 2;

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j + i < n; j += cycleLen) {
				ret.append(s.charAt(j + i));
				//此处判断当不是最后一行/第一行的时候,需要添加中间的元素(中间元素一行只有一调记录)
				if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) {
					ret.append(s.charAt(j + cycleLen - i));
				}
			}
		}
		return ret.toString();
	}
}
