package leetcode;

import java.lang.reflect.Member;

/**
 * @desc: 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * 示例:
 * 输入:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 * @author: zhongqionghua
 * @create: 2019/4/30 15:08
 */
public class TwentyThree {

	public static void main(String[] args){
		ListNode node1 = ListNode.initListNode(new int[]{1,6,9,10,15}, 0);
		ListNode node2 = ListNode.initListNode(new int[]{2,5,7,9,19}, 0);
		ListNode node3 = ListNode.initListNode(new int[]{1,4,7,9,10,40}, 0);
		ListNode[] listNodes = new ListNode[]{node1,node2, node3};
		System.out.println(mergeKLists(listNodes).toString());
	}



	public static ListNode mergeKLists(ListNode[] lists) {
		if(null == lists || lists.length <= 0){
			return new ListNode();
		}
		return mergeK(lists, 0, lists.length-1);
	}

	private static ListNode mergeK(ListNode[] node, int left, int right){
		if(left == right){
			return node[left];
		}
		int mid = (left + right) / 2;
		ListNode leftNode = mergeK(node, left, mid);
		ListNode rightNode = mergeK(node, mid+1, right);
		return TwentyOne.mergeTwoLists(leftNode, rightNode);

	}

}

