package leetcode;

import java.time.temporal.Temporal;

/**
 * @desc: 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 注:你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例:
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 * @author: zhongqionghua
 * @create: 2019/4/30 15:48
 */
public class TwentyFour {


	public static void main(String[] args) {
		ListNode node = ListNode.initListNode(new int[]{1, 6, 9, 10, 15}, 0);
		System.out.println(swapPairs2(node));
	}

	/**
	 * 迭代方法
	 *
	 * @param head
	 * @return
	 */
	public static ListNode swapPairs2(ListNode head) {
		ListNode dummy = new ListNode(0);
		ListNode p = dummy;
		while (head != null) {
			if (head != null && head.getNext() != null) {
				ListNode tmp = head.getNext();
				p.setNext(tmp);
				head.setNext(head.getNext().getNext());
				tmp.setNext(head);
				head = head.getNext();
				p = p.getNext().getNext();
			} else {
				p.setNext(head);
				head = head.getNext();
			}
		}
		return dummy.getNext();
	}

	/**
	 * 递归方法
	 *
	 * @param head
	 * @return
	 */
	public static ListNode swapPairs(ListNode head) {
		if (head == null || head.getNext() == null) {
			return head;
		}
		ListNode next = head.getNext();
		head.setNext(swapPairs(next.getNext()));
		next.setNext(head);
		return next;
	}
}
