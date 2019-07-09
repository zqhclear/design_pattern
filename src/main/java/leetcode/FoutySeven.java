package leetcode;

import com.alibaba.fastjson.JSONObject;

import javax.swing.*;
import java.util.*;

/**
 * @desc: 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * 示例:
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 * @author: zhongqionghua
 * @create: 2019/7/9 13:57
 */
public class FoutySeven {
	public static void main(String[] args) {
		int[] paramInts = new int[]{1, 2, 3};
//		System.out.println(JSONObject.toJSONString(permuteUniqueWithMySelf(paramInts)));
		System.out.println(JSONObject.toJSONString(permuteUniqueWithOptimize(paramInts)));
	}

	/**
	 * 优化
	 *
	 * @param nums
	 * @return
	 */
	public static List<List<Integer>> permuteUniqueWithOptimize(int[] nums) {
		if (nums == null || nums.length == 0) {
			return new ArrayList<>();
		}
		List<List<Integer>> res = new ArrayList<>();
		BFS(nums, res, 0);
		return res;
	}

	private static void BFS(int[] nums, List<List<Integer>> res, int index) {
		if (index == nums.length) {
			List<Integer> list = new ArrayList<>();
			Arrays.stream(nums).forEach(item -> list.add(item));
			res.add(list);
		} else {
			for (int i = index; i < nums.length; i++) {
				if (isUsed(nums, index, i)) {
					continue;
				}
				//此处变换位置,index--i,
				swap(nums, i, index);
				BFS(nums, res, index + 1);
				//执行完成一次之后,将位置恢复,以便下次查找
				swap(nums, index, i);
			}
		}
	}

	private static void swap(int[] nums, int swapIndex1, int swapIndex2){
		int temp = nums[swapIndex1];
		nums[swapIndex1] = nums[swapIndex2];
		nums[swapIndex2] = temp;
	}

	/**
	 * 去重: 1.去除和nums[start]相同的数字; 2.去除在之前已经出现过的nums[end]
	 *
	 * @param nums
	 * @param start
	 * @param end
	 * @return
	 */
	private static boolean isUsed(int[] nums, int start, int end) {
		for (int i = start; i < end; ++i) {
			if (nums[i] == nums[end]) {
				return true;
			}
		}
		return false;
	}

	public static List<List<Integer>> permuteUniqueWithMySelf(int[] nums) {
		List<List<Integer>> rst = new ArrayList<>();
		boolean[] visited = new boolean[nums.length];
		backtrace(nums, new ArrayList<>(nums.length), visited, rst);
		return rst;
	}

	/**
	 * @param nums
	 * @param temp
	 * @param visited    用来判断数组中哪些被访问过了,放过过了的置为1
	 * @param resultList
	 */
	public static void backtrace(int[] nums, List<Integer> temp, boolean[] visited, List<List<Integer>> resultList) {
		if (temp.size() == nums.length) {
			resultList.add(new ArrayList<>(temp));
			return;
		}
		Set set = new HashSet();
		for (int i = 0; i < nums.length; i++) {
			//visited代表当前这个分支还有哪些元素没有被使用过。
			//如果set里面已经有了这个元素，那么证明在这个位置已经出现过相同元素，直接剪枝。
			if (!visited[i] && !set.contains(nums[i])) {
				//这个set就是用于剪枝去重。
				set.add(nums[i]);
				temp.add(nums[i]);
				visited[i] = true;
				backtrace(nums, temp, visited, resultList);
				temp.remove(temp.size() - 1);
				visited[i] = false;
			}
		}
	}

}
