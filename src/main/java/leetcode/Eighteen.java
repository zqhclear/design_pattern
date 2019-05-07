package leetcode;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * @desc: 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * 注意：
 * 答案中不可以包含重复的四元组。
 * 示例：
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 * @author: zhongqionghua
 * @create: 2019/4/29 14:55
 */
public class Eighteen {

	public static void main(String[] args) {
		int[] paramArray = new int[]{-1, 2, 2, -5, 0, -1, 4};
		System.out.println(JSONObject.toJSONString(fourSum2(paramArray, 3)));
	}

	/**
	 * 优化版本
	 *
	 * @param nums
	 * @param target
	 * @return
	 */
	public static List<List<Integer>> fourSum2(int[] nums, int target) {
		List<List<Integer>> resultList = new LinkedList<>();
		Arrays.sort(nums);
		int numsLength = nums.length;
		for (int i = 0; i < numsLength - 3; i++) {
			//去重,相邻的两个相同的元素直接跳过
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			//已经排过序了,如果相邻的元素大 则直接结束
			if (nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
				break;
			}
			//已经排过序了,如果i和最后几个元素还是<target,则i++
			if (nums[i] + nums[numsLength - 1] + nums[numsLength - 2] + nums[numsLength - 3] < target) {
				continue;
			}
			for (int j = i + 1; j < numsLength - 2; j++) {
				//去重,相邻的两个相同的元素直接跳过
				if (j - i > 1 && nums[j] == nums[j - 1]) {
					continue;
				}
				//已经排过序了,如果相邻的元素大 则直接结束
				if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
					break;
				}
				//已经排过序了,如果i和最后几个元素还是<target,则i++
				if (nums[i] + nums[j] + nums[numsLength - 1] + nums[numsLength - 2] < target) {
					continue;
				}
				int left = j + 1;
				int right = numsLength - 1;
				while (left < right) {
					int tmp = nums[i] + nums[j] + nums[left] + nums[right];
					if (tmp == target) {
						List<Integer> tmpList = new LinkedList<>(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
						resultList.add(tmpList);
						while (left < right && nums[left] == nums[left + 1]) {
							left += 1;
						}
						while (left < right && nums[right] == nums[right - 1]) {
							right -= 1;
						}
						left += 1;
						right -= 1;
					} else if (tmp > target) {
						right -= 1;
					} else {
						left += 1;
					}
				}
			}

		}

		return resultList;
	}


	public static List<List<Integer>> fourSum(int[] nums, int target) {

		List<List<Integer>> l = new ArrayList<>();
		if (nums == null || nums.length < 4) {
			return l;
		}
		Arrays.sort(nums);
		Set<List<Integer>> valuesList = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
//			if (i > 0 && nums[i] == nums[i - 1]) {
//				// 去除重复答案
//				continue;
//			}
			//可以将四数之和变为三数之和=target
			int sum = target - nums[i];
			for (int j = i + 1; j < nums.length - 1; j++) {
				int left = j + 1;
				int right = nums.length - 1;
				while (left < right) {
					if (nums[j] + nums[left] + nums[right] == sum) {
						List<Integer> intList = Arrays.asList(nums[i], nums[j], nums[left], nums[right]);
						valuesList.add(intList);
//						do {
						left++;
//						} while (left < right && nums[left] == nums[left + 1]);

//						do {
						right--;
//						} while (left < right && nums[right] == nums[right - 1]);
					} else if (nums[j] + nums[left] + nums[right] > sum) {
//						do {
						right--;
//						} while (left < right && nums[right] == nums[right - 1]);
					} else {
//						do {
						left++;
//						} while (left < right && nums[left] == nums[left + 1]);
					}
				}
			}

		}
		l.addAll(valuesList);
		return l;
	}
}
