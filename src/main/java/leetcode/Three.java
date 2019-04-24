package leetcode;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.util.*;

/**
 * @description: 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * @author: zhongqionghua
 * @Date: 2019/4/22 18:02
 */
public class Three {
	public static void main(String[] args) {
		//String randomStr = "pwwkew";
		String randomStr = "1234dvdfdv";
		int maxLength = getMaxStr(randomStr);
		System.out.println(maxLength);
	}


	public static int getMaxStr(String str) {
		int maxLength = statisticsIndexOfStr2(str);
		return maxLength;

	}

	public static int statisticsIndexOfStr2(String str) {
		//maxLength:最大长度  wipeLength: 出现的最后的位置(下标 + 1),同时也是要舍弃的最大的段
		long startTime = System.currentTimeMillis();
		int maxLength = 0, wipeLength = 0;
		Map<Character, Integer> map = new HashMap<>(15);
		char[] chars = str.toCharArray();
		for (int j = 0; j < chars.length; j++) {
			if (map.containsKey(chars[j])) {
				wipeLength = Math.max(map.get(chars[j]), wipeLength);
			}
			maxLength = Math.max(maxLength, j - wipeLength + 1);
			//将遍历过的字符放置map中,并设置其出现的位置(下标+1)
			map.put(chars[j], j + 1);
		}
		System.out.println(System.currentTimeMillis() - startTime);
		System.out.println(JSONObject.toJSONString(map));
		return maxLength;
	}

	/**
	 * 查找每个元素的出现的位置
	 */
	public static void statisticsIndexOfStr(String str, Map<String, List<Integer>> charMap) {
		if (str == null) {
			return;
		} else if (str.length() <= 1) {
			return;
		}
		char[] strChars = str.toCharArray();
		int maxLength = 0;
		for (int i = 0; i < strChars.length; i++) {
			List<Integer> occurrenceList = charMap.get(strChars[i] + "");
			if (CollectionUtils.isEmpty(occurrenceList)) {
				occurrenceList = new ArrayList();
			}
			occurrenceList.add(i);
			charMap.put(strChars[i] + "", occurrenceList);
		}
		System.out.println(JSONObject.toJSONString(charMap));
		Set<Map.Entry<String, List<Integer>>> entrySet = charMap.entrySet();
		return;
	}

}
