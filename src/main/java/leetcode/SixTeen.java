package leetcode;

import java.util.Arrays;

/**
 * @desc: 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.
 * 与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
 * @author: zhongqionghua
 * @create: 2019/4/28 18:12
 */
public class SixTeen {

	public static void main(String[] args) {
		int[] intArrays = new int[]{-1, 0, 1, 1, 55};
		System.out.println(threeSumClosest(intArrays, 3));
	}

	public static int threeSumClosest(int[] nums, int target) {
		Arrays.sort(nums);
		int minNum = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length - 2; i++) {
			int left = i + 1;
			int right = nums.length - 1;
			while (left < right) {
				int subNum = nums[i] + nums[left] + nums[right] - target;
				if (subNum == 0) {
					return target;
				} else if (subNum > 0) {
					minNum = Math.abs(subNum) < Math.abs(minNum) ? subNum : minNum;
					right--;
				} else {
					minNum = Math.abs(subNum) < Math.abs(minNum) ? subNum : minNum;
					left++;
				}
			}
		}
		return target + minNum;
	}
}
