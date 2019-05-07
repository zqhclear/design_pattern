package leetcode;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @desc: 回溯算法
 * @author: zhongqionghua
 * @create: 2019/5/6 10:09
 */
public class Recall {

	public static void main(String[] args) {
		String a = "123456789asdfghjkl";
		System.out.println(a.length());
		List list = new ArrayList();
		pailie(a, "", list);
//		System.out.println(JSONObject.toJSONString(list));
		System.out.println(list.size());

	}

	/**
	 * 给出一个字符串,找出其所有的排列
	 *
	 * @param s
	 * @param temp
	 */
	public static void pailie(String s, String temp, List<String> list) {
		if (s.length() == 0) {
			list.add(temp);
			return;
		}
		//去掉String中的某个字母
		for (int i = 0; i < s.length(); i++) {
			String news = s.substring(0, i) + s.substring(i + 1);
			pailie(news, temp + s.charAt(i), list);
		}

	}
}
