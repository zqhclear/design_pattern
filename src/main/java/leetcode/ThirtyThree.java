package leetcode;

/**
 * @desc: 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 * @author: zhongqionghua
 * @create: 2019/5/8 10:38
 */
public class ThirtyThree {
	public static void main(String[] args) {
		int[] intArray = new int[]{4, 5, 6, 7, 0, 1, 2};
		System.out.println(search2(intArray, 0));
	}


	/**
	 * 思路三
	 *
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int search3(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		int left = 0;
		int right = nums.length;
		while (left < right) {
			int mid = left + (right - left) / 2;
			long num = (nums[mid] < nums[0]) == (target < nums[0])
					? nums[mid]
					: target < nums[0] ? Long.MIN_VALUE : Long.MAX_VALUE;
			if (num < target) {
				left = mid + 1;
			} else if (num > target) {
				right = mid;
			} else {
				return mid;
			}
		}
		return -1;
	}

	/**
	 * 思路2
	 *
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int search2(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		int left = 0;
		int right = nums.length - 1;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] == target) {
				return mid;
			} else if (nums[left] <= nums[mid]) {
				if (nums[left] <= target && target < nums[mid]) {
					right = mid - 1;
				} else {
					left = mid + 1;
				}

			} else if (nums[mid] < nums[right]) {
				if (nums[mid] < target && target <= nums[right]) {
					left = mid + 1;
				} else {
					right = mid - 1;
				}
			}
		}
		return nums[left] == target ? left : -1;
	}

	/**
	 * 使用二分法:
	 * 1.使用二分法先找到对调位置之后的分割点,即4,5,6,7,0,1,2的0的index
	 * 2.在根据该位置使用二分法找到具体位置
	 *
	 * @param nums
	 * @param target
	 * @return
	 */
	public static int search(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		int mid;
		while (left < right) {
			mid = (left + right) / 2;
			if (nums[mid] > nums[right]) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		int split_t = left;
		right = nums.length - 1;
		/*
		如果left==0表示该数组未经过旋转,其最小的值依旧在index=0处,该种情况可以直接按照二分法去查找
		否则,还需要判断target在哪一部分中(左,右)
		 */
		if (left != 0) {
			left = 0;
			//查看该值是在那一部分的数组中
			if (nums[split_t] <= target && target <= nums[right]) {
				//在右侧的小的一部分中
				left = split_t;
			} else {
				//在左侧的小的一部分中
				right = split_t - 1;
			}
		}
		while (left <= right) {
			mid = left + (right - left) / 2;
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] > target) {
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return -1;
	}

}
