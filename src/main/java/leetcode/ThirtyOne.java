package leetcode;

import org.apache.poi.ss.usermodel.RichTextString;

/**
 * @desc: 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须原地修改，只允许使用额外常数空间。
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 * @author: zhongqionghua
 * @create: 2019/5/7 14:48
 */
public class ThirtyOne {
	public static void main(String[] args) {
		int[] nums = new int[]{1, 3, 5, 8, 7, 6, 4};
		nextPermutation2(nums);
	}


	/**
	 * 和官方的解法原理一样
	 *
	 * @param nums
	 */
	public static void nextPermutation3(int[] nums) {
		if (nums == null || nums.length == 0) {
			return;
		}
		int firstIndex = -1;
		for (int i = nums.length - 2; i >= 0; i--) {
			if (nums[i] < nums[i + 1]) {
				firstIndex = i;
				break;
			}
		}
		if (firstIndex == -1) {
			reverse(nums, 0, nums.length - 1);
			return;
		}
		int secondIndex = -1;
		for (int i = nums.length - 1; i >= 0; i--) {
			if (nums[i] > nums[firstIndex]) {
				secondIndex = i;
				break;
			}
		}
		swap(nums, firstIndex, secondIndex);
		reverse(nums, firstIndex + 1, nums.length - 1);
		return;

	}

	private static void reverse(int[] nums, int i, int j) {
		while (i < j) {
			swap(nums, i++, j--);
		}
	}

	/**
	 * 官方解答
	 * 注:倒叙的时候才会出现不需要排列的情况
	 *
	 * @param nums
	 */
	public static void nextPermutation2(int[] nums) {
		int swapIndex = nums.length - 2;
		//从后往前,找到符合升序的index位置
		while (swapIndex >= 0 && nums[swapIndex + 1] <= nums[swapIndex]) {
			swapIndex--;
		}
		//swapIndex>=0表示有可以替换值
		if (swapIndex >= 0) {
			int j = nums.length - 1;
			//从末尾开始,往前第一个比swapIndex大的数,替换
			while (j >= 0 && nums[j] <= nums[swapIndex]) {
				j--;
			}
			swap(nums, swapIndex, j);
		}
		//上两步能确定swapIndex之后的数据是按照降序排序的
		//如果不是降序,则在当前swapIndex之前就会停止
		reverse(nums, swapIndex + 1);
	}

	private static void reverse(int[] nums, int start) {
		int i = start, j = nums.length - 1;
		while (i < j) {
			swap(nums, i, j);
			i++;
			j--;
		}
	}

	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}


	/**
	 * 自己解
	 *
	 * @param nums
	 */
	public static void nextPermutation(int[] nums) {
		for (int j = 0; j < nums.length; j++) {
			System.out.print(nums[j]);
		}
		int temp;
		int i = nums.length - 1;
		int left = 0;
		boolean flag = true;
		for (; i >= 1 && flag; i--) {
			left = i - 1;
			while (left >= 0) {
				if (nums[i] > nums[left]) {
					temp = nums[i];
					nums[i] = nums[left];
					nums[left] = temp;
					flag = false;
					break;
				}
				left--;
			}
		}
		for (int j = left + 1; j < nums.length - 1 && j >= 0 && !flag; j++) {
			if (nums[j] > nums[j + 1]) {
				temp = nums[j];
				nums[j] = nums[j + 1];
				nums[j + 1] = temp;
			}
		}
		System.out.print(" -> ");
		if (!flag) {
			for (int j = 0; j < nums.length; j++) {
				System.out.print(nums[j]);
			}
		} else {
			for (int j = nums.length - 1; j >= 0; j--) {
				System.out.print(nums[j]);
			}
		}
	}
}
