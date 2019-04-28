package leetcode;

/**
 * @desc: 编写一个函数来查找字符串数组中的最长公共前缀。
 * <p>
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1:
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * <p>
 * 示例 2:
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * <p>
 * 说明:
 * 所有输入只包含小写字母 a-z 。
 * @author: zhongqionghua
 * @create: 2019/4/28 11:28
 */
public class Thirteen {
	public static void main(String[] args) {
		String[] strs = new String[]{"abdsss", "abdsdsfasdf", "avdwqreew"};
		System.out.println(longestCommonPrefix(strs));
	}

	/**
	 * 二分法：根据最短的字符串,按照二分法去分别查找最短字符串是否是共有的
	 *
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix3(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		int minLen = Integer.MAX_VALUE;
		for (String str : strs) {
			minLen = Math.min(minLen, str.length());
		}
		int low = 1;
		int high = minLen;
		while (low <= high) {
			int middle = (low + high) / 2;
			if (isCommonPrefix(strs, middle)) {
				low = middle + 1;
			} else {
				high = middle - 1;
			}
		}
		return strs[0].substring(0, (low + high) / 2);
	}

	private boolean isCommonPrefix(String[] strs, int len) {
		String str1 = strs[0].substring(0, len);
		for (int i = 1; i < strs.length; i++){
			if (!strs[i].startsWith(str1)){
				return false;
			}
		}
		return true;
	}

	/**
	 * 字典树,还没弄明
	 *
	 * @param strs
	 * @return
	 */
	public static String longestCommonPrefix2(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		if (strs.length == 1) {
			return strs[0];
		}
		String q = strs[0];
		Trie trie = new Trie();
		for (int i = 1; i < strs.length; i++) {
			trie.insert(strs[i]);
		}
		return trie.searchLongestPrefix(q);
	}


	/**
	 * 分支方法:归并排序(nlogn)
	 *
	 * @param strs
	 * @return
	 */
	public static String longestCommonPrefix1(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		return longestCommonPrefix2(strs, 0, strs.length - 1);
	}

	private static String longestCommonPrefix2(String[] strs, int l, int r) {
		if (l == r) {
			return strs[l];
		} else {
			int mid = (l + r) / 2;
			String lcpLeft = longestCommonPrefix2(strs, l, mid);
			String lcpRight = longestCommonPrefix2(strs, mid + 1, r);
			return commonPrefix(lcpLeft, lcpRight);
		}
	}

	private static String commonPrefix(String left, String right) {
		int min = Math.min(left.length(), right.length());
		for (int i = 0; i < min; i++) {
			if (left.charAt(i) != right.charAt(i)) {
				return left.substring(0, i);
			}
		}
		return left.substring(0, min);
	}


	/**
	 * 自己解决的
	 *
	 * @param strs
	 * @return
	 */
	public static String longestCommonPrefix(String[] strs) {
		if (null == strs || strs.length == 0) {
			return "";
		}
		String sameStr = strs[0];
		for (int i = 1; i < strs.length; i++) {
			sameStr = getSameStr(sameStr, strs[i]);
		}
		return sameStr;
	}

	private static String getSameStr(String strOne, String strTwo) {
		StringBuilder sameStr = new StringBuilder();
		for (int i = 0; i < strOne.length() && i < strTwo.length(); i++) {
			if (strOne.charAt(i) != strTwo.charAt(i)) {
				break;
			}
			sameStr.append(strOne.charAt(i));
		}
		return sameStr.toString();
	}
}

class TrieNode {

	// R links to node children
	private TrieNode[] links;

	private final int R = 26;

	private boolean isEnd;

	private int size;

	public TrieNode() {
		links = new TrieNode[R];
	}

	public int getLinks() {
		return links.length;
	}


	public boolean containsKey(char ch) {
		return links[ch - 'a'] != null;
	}

	public TrieNode get(char ch) {
		return links[ch - 'a'];
	}

	public void put(char ch, TrieNode node) {
		links[ch - 'a'] = node;
		size++;
	}

	public void setEnd() {
		isEnd = true;
	}

	public boolean isEnd() {
		return isEnd;
	}
}

class Trie {
	private TrieNode root;

	public Trie() {
		root = new TrieNode();
	}

	// Inserts a word into the trie.
	public void insert(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char currentChar = word.charAt(i);
			if (!node.containsKey(currentChar)) {
				node.put(currentChar, new TrieNode());
			}
			node = node.get(currentChar);
		}
		node.setEnd();
	}

	private TrieNode searchPrefix(String word) {
		TrieNode node = root;
		for (int i = 0; i < word.length(); i++) {
			char curLetter = word.charAt(i);
			if (node.containsKey(curLetter)) {
				node = node.get(curLetter);
			} else {
				return null;
			}
		}
		return node;
	}

	// Returns if the word is in the trie.
	public boolean search(String word) {
		TrieNode node = searchPrefix(word);
		return node != null && node.isEnd();
	}

	public String searchLongestPrefix(String word) {
		TrieNode node = root;
		StringBuilder prefix = new StringBuilder();
		for (int i = 0; i < word.length(); i++) {
			char curLetter = word.charAt(i);
			if (node.containsKey(curLetter) && (node.getLinks() == 1) && (!node.isEnd())) {
				prefix.append(curLetter);
				node = node.get(curLetter);
			} else {
				return prefix.toString();
			}
		}
		return prefix.toString();
	}
}

