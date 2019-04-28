package leetcode;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.List;

/**
 * @desc: 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * @author: zhongqionghua
 * @create: 2019/4/28 15:21
 */
public class Fifteen {
	public static void main(String[] args) {
		int[] intArray = new int[]{3, -2, 0, 9, -10, 6, -5, -3, -5, -3, 9, -3, 4, 4, -6, -1, 8, 9, -2, -6, 5, -8, 6};
		List l = Arrays.asList(1, -1, 1);
		l.sort(null);
		List l2 = Arrays.asList(-1, 1, 1);
		l2.sort(null);
		System.out.println(l.equals(l2));
		System.out.println(JSONObject.toJSONString(threeSum2(intArray)));

		System.out.println(l.equals(l2));
		System.out.println(JSONObject.toJSONString(threeSum(intArray)));

//		int[] sortArray = new int[]{-10,-8,-6,-6,-5,-5,-3,-3,-3,-2,-2,-1,0,3,4,4,5,6,6,8,9,9,9};
//		System.out.println(getIndexOfSub(5, sortArray));
	}

	public static List<List<Integer>> threeSum2(int[] nums) {
		List<List<Integer>> list = new ArrayList<>();
		int len = nums.length;
		if (nums == null || len < 3) {
			return list;
		}
		// 排序
		Arrays.sort(nums);
		for (int i = 0; i < len - 2; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) {
				// 去除重复答案
				continue;
			}
			int j = i + 1;
			int k = len - 1;
			while (j < k) {
				// 使用两个指针逼近
				if (nums[j] + nums[k] + nums[i] == 0) {
					List<Integer> slot = Arrays.asList(nums[i], nums[j], nums[k]);
					list.add(slot);
					// 继续逼近，寻找下一组答案
					do {
						k--;
					} while (j < k && nums[k] == nums[k + 1]);
					do {
						j++;
					} while (j < k && nums[j] == nums[j - 1]);
				} else if (nums[j] + nums[k] + nums[i] > 0) {
					do {
						k--;
					} while (j < k && nums[k] == nums[k + 1]);
				} else {
					do {
						j++;
					} while (j < k && nums[j] == nums[j - 1]);
				}
			}
		}
		return list;
	}


	/**
	 * 超时
	 *
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> threeSum(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> valuesList = new ArrayList<>();
		int sum;
		int aNum;
		int index;
		List<Integer> tempValueList = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			sum = 0 - nums[i];
			for (int j = i+1; j < nums.length; j++) {
				aNum = sum - nums[j];
				index = getIndexOfSub(aNum, nums);
				if (index >= 0 && index != i && index != j) {
					compare(i, j, index, nums, valuesList, tempValueList);
				}
			}
		}
		return valuesList;
	}

	private static void compare(int i, int j, int index, int[] nums, List<List<Integer>> lists, List<Integer> temValueList) {
		temValueList = Arrays.asList(nums[i], nums[j], nums[index]);
		temValueList.sort(null);
		if (lists.contains(temValueList)) {
			return;
		}
		lists.add(temValueList);
	}

	private static int getIndexOfSub(int target, int[] nums) {
		int left = 0;
		int right = nums.length - 1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (nums[mid] > target) {
				right = mid - 1;
			} else if (nums[mid] < target) {
				left = mid + 1;
			} else {
				return mid;
			}
		}
		return -1;
	}
}
