package leetcode;

import java.util.List;

/**
 * @desc: 将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * @author: zhongqionghua
 * @create: 2019/4/29 18:08
 */
public class TwentyOne {
	public static void main(String[] args) {
		int[] intArray1 = new int[]{1, 3, 5, 7, 9};
		ListNode node1 = ListNode.initListNode(intArray1, 0);
		int[] intArray2 = new int[]{2, 5, 8, 10};
		ListNode node2 = ListNode.initListNode(intArray2, 0);
		System.out.println(mergeTwoLists(node1, node2).toString());

	}

	/**
	 * 归并排序方法
	 *
	 * @param l1
	 * @param l2
	 * @return
	 */
	public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode newNode = new ListNode(0);
		ListNode nodeTemp = newNode;
		while (l1 != null && l2 != null) {
			if (l1.getVal() <= l2.getVal()) {
				nodeTemp.setNext(l1);
				l1 = l1.getNext();
			} else {
				nodeTemp.setNext(l2);
				l2 = l2.getNext();
			}
			nodeTemp = nodeTemp.getNext();
		}
		if (l1 != null) {
			nodeTemp.setNext(l1);
		}
		if (l2 != null) {
			nodeTemp.setNext(l2);
		}
		return newNode.getNext();
	}
}
