package leetcode;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @desc: 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * @author: zhongqionghua
 * @create: 2019/4/29 17:20
 */
public class Twenty {

	static HashMap<Character, Character> parenthesisMap = new HashMap<Character, Character>() {{
		put(')', '(');
		put('}', '{');
		put(']', '[');
	}};


	public static void main(String[] args) {
		System.out.println(isValid2("{}[]()([])"));
	}

	/**
	 * 优化
	 *
	 * @param s
	 * @return
	 */
	public static boolean isValid2(String s) {
		if (s.length() % 2 == 1) {
			return false;
		}
		char[] chars = new char[s.length() / 2];
		int position = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			Character eliminationItem = parenthesisMap.get(c);
			if (eliminationItem != null) {
				if (position == 0) {
					return false;
				}
				Character pop = chars[position - 1];
				if (pop != eliminationItem.charValue()) {
					return false;
				}
				position--;
			} else {
				if (position >= chars.length) {
					return false;
				}
				chars[position] = c;
				position++;
			}
		}
		if (position == 0) {
			return true;
		}
		return false;
	}

	public static boolean isValid(String s) {
		List<Character> charsList = new ArrayList();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (parenthesisMap.containsKey(c)) {
				char topElement = CollectionUtils.isEmpty(charsList) ? '#' : charsList.get(charsList.size() - 1);
				if (topElement != parenthesisMap.get(c)) {
					return false;
				}
				charsList.remove(charsList.size() - 1);
			} else {
				charsList.add(c);
			}
		}
		return charsList.isEmpty();
	}
}
