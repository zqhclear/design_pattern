package leetcode;

import java.util.*;

/**
 * @desc: 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * <p>
 * 示例:
 * <p>
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * <p>
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 * @author: zhongqionghua
 * @create: 2019/7/8 14:03
 */
public class FortyNine {

	public static void main(String[] args) {
		String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
		System.out.println(groupAnagramsWithOptimize(strs));
	}


	/**
	 * 优化
	 *
	 * @param strs
	 * @return
	 */
	public static List<List<String>> groupAnagramsWithOptimize(String[] strs) {
		Map<String, List<String>> map = new HashMap<>(20);
		for (int i = 0; i < strs.length; i++) {
			char[] chs = strs[i].toCharArray();
			Arrays.sort(chs);
			String str = String.valueOf(chs);
			if (!map.containsKey(str)) {
				map.put(str, new ArrayList<>());
			}
			map.get(str).add(strs[i]);
		}
		return new ArrayList<>(map.values());
	}


	/**
	 * 自己做的 超出时间限制
	 *
	 * @param strs
	 * @return
	 */
	public static List<List<String>> groupAnagramsWithMySelf(String[] strs) {
		List<String> paramList = new ArrayList<>(Arrays.asList(strs));
		Map<Integer, Integer> skipMap = new HashMap<>();
		List<List<String>> resultList = new ArrayList<>();
		for (int i = 0; i < paramList.size(); i++) {
			if (skipMap.containsKey(i)) {
				continue;
			}
			List<String> record = new ArrayList<>(Arrays.asList(paramList.get(i)));
			for (int j = i + 1; j < paramList.size(); j++) {
				if (isDifferentDigits(paramList.get(i), paramList.get(j))) {
					skipMap.put(j, j);
					record.add(paramList.get(j));
				}
			}
			resultList.add(record);
		}
		return resultList;
	}

	/**
	 * 是否为异位数--优化
	 *
	 * @param str
	 * @param str2
	 * @return
	 */
	public static boolean isDifferentDigitsWithOptimize(String str, String str2) {
		if (str == null || str2 == null) {
			return Objects.equals(str, str2);
		}
		char[] chs = str.toCharArray();
		char[] chs2 = str2.toCharArray();
		Arrays.sort(chs);
		Arrays.sort(chs2);
		if (String.valueOf(chs).equals(String.valueOf(chs2))) {
			return true;
		}
		return false;
	}

	/**
	 * 判断两数是否为异位数
	 *
	 * @param str
	 * @param str2
	 * @return
	 */
	public static boolean isDifferentDigits(String str, String str2) {
		if (str.length() != str2.length()) {
			return false;
		}
		Map<Character, Integer> ariseMap = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			Integer count1 = ariseMap.get(str.charAt(i));
			ariseMap.put(str.charAt(i), null == count1 ? 1 : count1 + 1);

			Integer count2 = ariseMap.get(str2.charAt(i));
			ariseMap.put(str2.charAt(i), null == count2 ? -1 : count2 - 1);
		}

		Set<Map.Entry<Character, Integer>> set = ariseMap.entrySet();
		Iterator<Map.Entry<Character, Integer>> iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry<Character, Integer> entry = iterator.next();
			if (entry.getValue() != 0) {
				return false;
			}
		}
		return true;
	}
}
