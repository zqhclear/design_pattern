package leetcode;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @desc: 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 * 示例 1：
 * 输入：
 * s = "barfoothefoobarman",
 * words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoor" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 * 输入：
 * s = "wordgoodgoodgoodbestword",
 * words = ["word","good","best","word"]
 * 输出：[]
 * @author: zhongqionghua
 * @create: 2019/5/6 10:40
 */
public class Thirty {

	public static void main(String[] args) {
//		String s = "pjzkrkevzztxductzzxmxsvwjkxpvukmfjywwetvfnujhweiybwvvsrfequzkhossmootkmyxgjgfordrpapjuunmqnxxdrqrfgkrsjqbszgiqlcfnrpjlcwdrvbumtotzylshdvccdmsqoadfrpsvnwpizlwszrtyclhgilklydbmfhuywotjmktnwrfvizvnmfvvqfiokkdprznnnjycttprkxpuykhmpchiksyucbmtabiqkisgbhxngmhezrrqvayfsxauampdpxtafniiwfvdufhtwajrbkxtjzqjnfocdhekumttuqwovfjrgulhekcpjszyynadxhnttgmnxkduqmmyhzfnjhducesctufqbumxbamalqudeibljgbspeotkgvddcwgxidaiqcvgwykhbysjzlzfbupkqunuqtraxrlptivshhbihtsigtpipguhbhctcvubnhqipncyxfjebdnjyetnlnvmuxhzsdahkrscewabejifmxombiamxvauuitoltyymsarqcuuoezcbqpdaprxmsrickwpgwpsoplhugbikbkotzrtqkscekkgwjycfnvwfgdzogjzjvpcvixnsqsxacfwndzvrwrycwxrcismdhqapoojegggkocyrdtkzmiekhxoppctytvphjynrhtcvxcobxbcjjivtfjiwmduhzjokkbctweqtigwfhzorjlkpuuliaipbtfldinyetoybvugevwvhhhweejogrghllsouipabfafcxnhukcbtmxzshoyyufjhzadhrelweszbfgwpkzlwxkogyogutscvuhcllphshivnoteztpxsaoaacgxyaztuixhunrowzljqfqrahosheukhahhbiaxqzfmmwcjxountkevsvpbzjnilwpoermxrtlfroqoclexxisrdhvfsindffslyekrzwzqkpeocilatftymodgztjgybtyheqgcpwogdcjlnlesefgvimwbxcbzvaibspdjnrpqtyeilkcspknyylbwndvkffmzuriilxagyerjptbgeqgebiaqnvdubrtxibhvakcyotkfonmseszhczapxdlauexehhaireihxsplgdgmxfvaevrbadbwjbdrkfbbjjkgcztkcbwagtcnrtqryuqixtzhaakjlurnumzyovawrcjiwabuwretmdamfkxrgqgcdgbrdbnugzecbgyxxdqmisaqcyjkqrntxqmdrczxbebemcblftxplafnyoxqimkhcykwamvdsxjezkpgdpvopddptdfbprjustquhlazkjfluxrzopqdstulybnqvyknrchbphcarknnhhovweaqawdyxsqsqahkepluypwrzjegqtdoxfgzdkydeoxvrfhxusrujnmjzqrrlxglcmkiykldbiasnhrjbjekystzilrwkzhontwmehrfsrzfaqrbbxncphbzuuxeteshyrveamjsfiaharkcqxefghgceeixkdgkuboupxnwhnfigpkwnqdvzlydpidcljmflbccarbiegsmweklwngvygbqpescpeichmfidgsjmkvkofvkuehsmkkbocgejoiqcnafvuokelwuqsgkyoekaroptuvekfvmtxtqshcwsztkrzwrpabqrrhnlerxjojemcxel";
//		String[] words = new String[]{"dhvf", "sind", "ffsl", "yekr", "zwzq", "kpeo", "cila", "tfty", "modg", "ztjg", "ybty", "heqg", "cpwo", "gdcj", "lnle", "sefg", "vimw", "bxcb"};
		String s = "barfoothefoobarman";
		String[] words = new String[]{"foo", "bar"};
		long startTime = System.currentTimeMillis();
		List l = findSubstring3(s, words);
		System.out.println(System.currentTimeMillis() - startTime);
		System.out.println(JSONObject.toJSONString(l));
	}


	/**
	 * 再优化,该算法将findSubstring2(...)中的三种情况合并在了一起,不清除hasMap中的值
	 *
	 * @param s
	 * @param words
	 * @return
	 */
	public static List<Integer> findSubstring3(String s, String[] words) {
		List<Integer> res = new ArrayList<>();
		if (s == null || s.length() == 0 || words == null || words.length == 0) {
			return res;
		}
		HashMap<String, Integer> map = new HashMap<>(20);
		int one_word = words[0].length();
		int word_num = words.length;
		for (String word : words) {
			map.put(word, map.getOrDefault(word, 0) + 1);
		}
		for (int i = 0; i < one_word; i++) {
			int left = i, right = i, count = 0;
			HashMap<String, Integer> tmp_map = new HashMap<>(20);
			while (right + one_word <= s.length()) {
				String w = s.substring(right, right + one_word);
				right += one_word;
				if (!map.containsKey(w)) {
					count = 0;
					left = right;
					tmp_map.clear();
				} else {
					tmp_map.put(w, tmp_map.getOrDefault(w, 0) + 1);
					count++;
					while (tmp_map.getOrDefault(w, 0) > map.getOrDefault(w, 0)) {
						String t_w = s.substring(left, left + one_word);
						count--;
						tmp_map.put(t_w, tmp_map.getOrDefault(t_w, 0) - 1);
						left += one_word;
					}
					if (count == word_num) {
						res.add(left);
					}
				}
			}
		}
		return res;
	}

	/**
	 * 方法二:遍历
	 *
	 * @param s
	 * @param words
	 * @return
	 */
	public static List<Integer> findSubstring2(String s, String[] words) {
		List<Integer> res = new ArrayList<>();
		int wordNum = words.length;
		if (wordNum == 0) {
			return res;
		}
		int wordLen = words[0].length();
		//保存每个word在words中出现的次数
		HashMap<String, Integer> allWords = new HashMap<>(30);
		for (String w : words) {
			allWords.put(w, allWords.getOrDefault(w, 0) + 1);
		}
		//将所有移动分成 wordLen 类情况
		HashMap<String, Integer> hasWords = new HashMap<>(100);
		int maxForeach = s.length() - wordNum * wordLen;
		for (int j = 0; j < wordLen; j++) {
			hasWords.clear();
			//记录当前 HashMap2（这里的 hasWords 变量）中有多少个单词
			int num = 0;
			//每次移动一个单词长度
			for (int i = j; i <= maxForeach; i = i + wordLen) {
				//防止情况三移除后，情况一继续移除
				int removeNum = 0;
				while (num < wordNum) {
					String concurrentWord = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
					if (allWords.containsKey(concurrentWord)) {
						removeNum = 0;
						hasWords.put(concurrentWord, hasWords.getOrDefault(concurrentWord, 0) + 1);
						//出现情况三，遇到了符合的单词，但是次数超了,则一直往后移动,直到次数符合
						if (hasWords.get(concurrentWord) > allWords.get(concurrentWord)) {
							//一直移除单词，直到次数符合了
							while (hasWords.get(concurrentWord) > allWords.get(concurrentWord)) {
								String firstWord = s.substring(i + removeNum * wordLen, i + (removeNum + 1) * wordLen);
								hasWords.put(firstWord, hasWords.get(firstWord) - 1);
								removeNum++;
							}
							//加 1 是因为我们把当前单词加入到了 HashMap 2 中
							num = num - removeNum;
							num++;
							//这里依旧是考虑到了最外层的 for 循环，看情况二的解释
							i = i + (removeNum - 1) * wordLen;
							break;
						}
						num++;
					} else {
						/*
						出现情况二，遇到了不匹配的单词，直接将 i 移动到该单词的后边（但其实这里只是移动到了
						出现问题单词的地方，因为最外层有 for 循环， i 还会移动一个单词然后刚好就移动到了单词后边）
						 */
						hasWords.clear();
						i = i + num * wordLen;
						num = 0;
						break;
					}
				}
				if (num == wordNum) {
					res.add(i);
				}
				//出现情况一，子串完全匹配，我们将上一个子串的第一个单词从 HashMap2 中移除
				if (num > 0 && removeNum == 0) {
					String firstWord = s.substring(i, i + wordLen);
					hasWords.put(firstWord, hasWords.get(firstWord) - 1);
					num = num - 1;
				}
			}
		}
		return res;
	}


	/**
	 * 方法一:遍历resourceStr
	 *
	 * @param s
	 * @param words
	 * @return
	 */
	public static List<Integer> findSubstring1(String s, String[] words) {
		List<Integer> res = new ArrayList<>();
		int wordNum = words.length;
		if (wordNum == 0) {
			return res;
		}
		int wordLen = words[0].length();
		//HashMap1 存所有单词,并保存其出现的次数
		HashMap<String, Integer> allWords = new HashMap<>(30);
		for (String w : words) {
			allWords.put(w, allWords.getOrDefault(w, 0) + 1);
		}
		//遍历所有子串
		int maxForeach = s.length() - wordNum * wordLen;
		//HashMap2 存当前扫描的字符串含有的单词
		HashMap<String, Integer> hasWords = new HashMap<>(100);
		for (int i = 0; i <= maxForeach; i++) {
			hasWords.clear();
			int num = 0;
			//判断该子串是否符合
			while (num < wordNum) {
				String word = s.substring(i + num * wordLen, i + (num + 1) * wordLen);
				//判断该单词在 HashMap1 中
				if (!allWords.containsKey(word)) {
					break;
				}
				hasWords.put(word, hasWords.getOrDefault(word, 0) + 1);
				//判断当前单词的 value 和 HashMap1 中该单词的 value,如果已出现次数>words中出现的次数,则表明不符合了,直接break往后找
				if (hasWords.get(word) > allWords.get(word)) {
					break;
				}
				num++;
			}
			//判断是不是所有的单词都符合条件
			if (num == wordNum) {
				res.add(i);
			}
		}
		return res;
	}


	/**
	 * 失败,时间过长,卡死了
	 *
	 * @param s
	 * @param words
	 * @return
	 */
	public static List<Integer> findSubstring(String s, String[] words) {
		List<Integer> indexList = new ArrayList<>();
		if (null == s || s.length() == 0) {
			return indexList;
		}
		if (null == words || words.length == 0) {
			return indexList;
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			stringBuilder.append(words[i]);
		}
		List<String> list = new ArrayList<>();
		pailie(stringBuilder.toString(), "", list, words[0].length());
		System.out.println(list.size() + "\n" + JSONObject.toJSONString(list));
		for (int i = 0; i < list.size(); i++) {
			strStr2(s, list.get(i), indexList);
		}
		return indexList;
	}

	public static void pailie(String oldStr, String temp, List<String> list, int wordNum) {
		if (oldStr.length() == 0) {
			list.add(temp);
			return;
		}
		//去掉String中的某个字母
		for (int i = 0; i < oldStr.length(); i = i + wordNum) {
			String news = oldStr.substring(0, i) + oldStr.substring(i + wordNum);
			pailie(news, temp + oldStr.substring(i, i + wordNum), list, wordNum);
		}
	}

	public static void strStr2(String haystack, String needle, List<Integer> list) {
		if (needle.equals("")) {
			return;
		}
		char[] haystackChars = haystack.toCharArray();
		char[] needleChars = needle.toCharArray();
		int max = haystackChars.length - needleChars.length;
		if (max < 0) {
			return;
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
			if (j == needleChars.length && !list.contains(i)) {
				list.add(i);
			}
		}
	}
}
