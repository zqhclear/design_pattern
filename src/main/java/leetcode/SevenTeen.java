package leetcode;

import com.alibaba.fastjson.JSONObject;

import javax.print.DocFlavor;
import java.awt.dnd.InvalidDnDOperationException;
import java.util.*;
import java.util.concurrent.ForkJoinPool;

/**
 * @desc: 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 * @author: zhongqionghua
 * @create: 2019/4/29 10:09
 */
public class SevenTeen {

	static List<String> charsConstantList = Arrays.asList("abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz");

	public static void main(String[] args) {
		String str = "234";
		System.out.println(JSONObject.toJSONString(letterCombinations2(str)));
	}


	/**
	 * 使用递归方法  优解
	 *
	 * @param digits
	 * @return
	 */
	private static List<String> letterCombinations2(String digits) {
		List<String> list = new ArrayList<>();
		String[] charsArray = new String[digits.length()];
		if (charsArray.length == 0) {
			return list;
		}
		for (int i = 0; i < digits.length(); i++) {
			charsArray[i] = charsConstantList.get((int) digits.charAt(i) - 50);
		}
		list = getStringWithFor(charsArray, 0, list, "");
		return list;
	}

	private static List<String> getStringWithFor(String[] charsArrays, int index, List<String> list, String tempStr) {
		//遍历原始的数字解析的字符串,知道最后
		if (index < charsArrays.length - 1) {
			for (int j = 0; j < charsArrays[index].length(); j++) {
				getStringWithFor(charsArrays, index + 1, list, tempStr + charsArrays[index].charAt(j));
			}
		} else {
			for (int j = 0; j < charsArrays[index].length(); j++) {
				list.add(tempStr + charsArrays[index].charAt(j));
			}
		}

		return list;
	}

	/**
	 * 使用循环计算
	 *
	 * @param digits
	 */
	public static List<String> letterCombinations(String digits) {
		List<String> result = new ArrayList<>();
		List<List<String>> constant = Arrays.asList(Arrays.asList("a", "b", "c"), Arrays.asList("d", "e", "f"),
				Arrays.asList("g", "h", "i"), Arrays.asList("j", "k", "l"), Arrays.asList("m", "n", "o"),
				Arrays.asList("p", "q", "r", "s"), Arrays.asList("t", "u", "v"), Arrays.asList("w", "x", "y", "z"));
		int len = digits.length();
		for (int i = 0; i < len; i++) {
			//获取当前数字
			int index = (int) digits.charAt(i) - 50;
			if (result.size() == 0) {
				for (int k = 0; k < constant.get(index).size(); k++) {
					result.add(constant.get(index).get(k));
				}
			} else {
				int s = result.size();
				for (int j = 0; j < s; j++) {
					//保存当前的result的中的值,后续还要往后面加字符 eg:ad + g
					String tmp = result.get(0);
					//移除当前的result的值
					result.remove(result.remove(0));
					for (int k = 0; k < constant.get(index).size(); k++) {
						StringBuilder stringBuilder = new StringBuilder(tmp);
						stringBuilder.append(constant.get(index).get(k));
						result.add(stringBuilder.toString());
					}
				}
			}
		}
		return result;
	}
}
