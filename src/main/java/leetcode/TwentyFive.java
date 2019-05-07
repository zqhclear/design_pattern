package leetcode;

/**
 * @desc: 给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。
 * <p>
 * 示例 :
 * 给定这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * <p>
 * 说明 :
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * @author: zhongqionghua
 * @create: 2019/4/30 16:42
 */
public class TwentyFive {

	public static void main(String[] args) {
		ListNode node = ListNode.initListNode(new int[]{1, 6, 9, 10, 15}, 0);
		System.out.println(reverseKGroup2(node, 3).toString());
	}

	public static ListNode reverseKGroup2(ListNode head, int k) {
		//设置前直接点,以便后期直接返回该节点的next
		ListNode dummy = new ListNode(0);
		dummy.setNext(head);
		ListNode dummyTemp = dummy;
		//保存已翻转的长度为k的链
		ListNode reversedList = null;
		//保存翻转的最后节点
		ListNode notReversedListNode = head;
		int num = 0;
		while (head != null) {
			num++;
			ListNode node = head;
			head = node.getNext();
			node.setNext(reversedList);
			reversedList = node;
			if (num % k == 0) {
				dummyTemp.setNext(reversedList);
				reversedList = null;
				dummyTemp = notReversedListNode;
				notReversedListNode = head;
			}
		}
		//最后剩下的元素不满足翻转条件时,需要整合
		if (num % k != 0) {
			recall(dummyTemp, reversedList).setNext(null);
		}
		return dummy.getNext();
	}

	public static ListNode recall(ListNode last, ListNode node) {
		if (node.getNext() != null) {
			recall(last, node.getNext()).setNext(node);
			return node;
		} else {
			last.setNext(node);
			return node;
		}
	}

	/**
	 * 递归,不过不满足空间复杂度O(1)
	 *
	 * @param head
	 * @param k
	 * @return
	 */
	public static ListNode reverseKGroup(ListNode head, int k) {
		ListNode prev = null;
		ListNode cur = head;
		ListNode next = null;
		ListNode check = head;
		int canProceed = 0;
		int count = 0;
		// 检查链表长度是否满足翻转
		while (canProceed < k && check != null) {
			check = check.getNext();
			canProceed++;
		}
		// 满足条件，进行翻转
		if (canProceed == k) {
			while (count < k && cur != null) {
				next = cur.getNext();
				cur.setNext(prev);
				prev = cur;
				cur = next;
				count++;
			}
			if (next != null) {
				// head 为链表翻转后的尾节点
				head.setNext(reverseKGroup(next, k));
			}
			// prev 为链表翻转后的头结点
			return prev;
		} else {
			// 不满住翻转条件，直接返回 head 即可
			return head;
		}
	}
}
