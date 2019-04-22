package leetcode;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * @description: 给定一个整数数组 nums 和一个目标值 target，
 * 请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * @author: zhongqionghua
 * @Date: 2019/4/22 14:45
 */
public class First {

	public static void main(String[] args){
		int[] intArray = {87, 45, 78, 32, 17, 65, 53, 9, 122, 133};

		//第一种方式:失败的方式
		//System.out.println(JSONObject.toJSONString(indexArray(intArray, 62)));

		//two
		System.out.println(JSONObject.toJSONString(indexArray2(intArray, 62)));
	}


	public static int[] indexArray2(int[] nums, int target){
			Map<Integer, Integer> map = new HashMap<>();
			for (int i = 0; i < nums.length; i++) {
				int complement = target - nums[i];
				if (map.containsKey(complement)) {
					return new int[] { map.get(complement), i };
				}
				map.put(nums[i], i);
			}
			return null;
	}

	public static int[] indexArray(int[] nums, int target) {
		insertSortArray(nums);
		for (int i = 0; i <= nums.length / 2; i++) {
			int index = binarySelect(nums, target - nums[i]);
			if (index > 0) {
				return new int[]{i, index};
			}
		}
		return null;
	}

	/**
	 * 使用二分法查找制定元素下标 返回-1表示没有找到
	 *
	 * @param intArray
	 * @param target
	 * @return
	 */
	public static int binarySelect(int[] intArray, int target) {
		if (intArray == null || intArray.length == 0) {
			return -1;
		}
		int left = 0;
		int right = intArray.length - 1;
		int mid;
		while (left <= right) {
			mid = (left + right) / 2;
			if (intArray[mid] > target) {
				right = (left + mid) / 2;
			} else if (intArray[mid] < target) {
				left = (right + mid) / 2;
			} else {
				return mid;
			}
		}
		return -1;

	}

	/**
	 * 直接插入排序数组
	 *
	 * @param intArray
	 */
	public  static void insertSortArray(int[] intArray) {
		List l = new ArrayList<>();
		if (intArray == null || intArray.length == 0) {
			return;
		}
		for (int i = 0; i < intArray.length; i++) {
			int concurrentValue = intArray[i];
			int j = i - 1;
			while (j >= 0 && intArray[j] > concurrentValue) {
				intArray[j + 1] = intArray[j];
				j--;
			}
			intArray[j + 1] = concurrentValue;
		}
	}
}
