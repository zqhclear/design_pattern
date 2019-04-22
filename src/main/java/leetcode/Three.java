package leetcode;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	public static void main(String[] args){
		String randomStr = "111111";
		int maxLength = getMaxStr(randomStr);
		System.out.println(maxLength);
	}


	public static int getMaxStr(String str){
		int maxLength = statisticsIndexOfStr(str, new HashMap<>(15));
		return maxLength;

	}

	/**
	 * 查找每个元素的出现的位置
	 */
	public static int statisticsIndexOfStr(String str, Map<String, List> charMap){
		if(str == null || str.length() == 0){
			return 0;
		}
		char[] strChars = str.toCharArray();
		int maxLength = -1;
		for (int i = 0; i < strChars.length; i++) {
			List<Integer> occurrenceList = charMap.get(strChars[i] + "");
			if(CollectionUtils.isEmpty(occurrenceList)){
				occurrenceList = new ArrayList();
			}
			occurrenceList.add(i);
			charMap.put(strChars[i] + "", occurrenceList);
			if(occurrenceList.size() > 1){
				int length = occurrenceList.get(occurrenceList.size() - 1);
				//需要找出现两次以上,并且出现次数的index最小的
				if(maxLength == -1){
					maxLength = length;
				}else{
					maxLength = maxLength < length ? maxLength : length;
				}
			}
		}
		System.out.println(JSONObject.toJSONString(charMap));
		return maxLength;
	}

}
