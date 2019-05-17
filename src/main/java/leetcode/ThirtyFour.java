package leetcode;

import com.alibaba.fastjson.JSONObject;

/**
 * @desc: 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 如果数组中不存在目标值，返回 [-1, -1]。
 * 示例 1:
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 * @author: zhongqionghua
 * @create: 2019/5/8 14:26
 */
public class ThirtyFour {
	public static void main(String[] args) {
		int[] intArray = new int[]{1};
		System.out.println(JSONObject.toJSONString(searchRange(intArray, 1)));
	}

	public static int[] searchRange(int[] nums, int target) {
		int[] indexWithMinAndMax = new int[]{-1, -1};
		int left = 0;
		int right = nums.length - 1;
		int mid = 0;
		while (left <= right) {
			mid = (left + right) / 2;
			if (nums[mid] == target) {
				indexWithMinAndMax[0] = mid;
				indexWithMinAndMax[1] = mid;
				while (indexWithMinAndMax[1] + 1 < nums.length && target == nums[indexWithMinAndMax[1] + 1]) {
					indexWithMinAndMax[1]++;
				}
				while (indexWithMinAndMax[0] - 1 >= 0 && target == nums[indexWithMinAndMax[0] - 1]) {
					indexWithMinAndMax[0]--;
				}
				break;
			} else if (nums[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return indexWithMinAndMax;
	}
}
