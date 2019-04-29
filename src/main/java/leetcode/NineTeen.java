package leetcode;

import binarytree.Node;
import com.alibaba.fastjson.JSONObject;
import com.sun.corba.se.impl.presentation.rmi.IDLTypeException;

import java.io.File;
import java.util.List;

/**
 * @desc: 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * <p>
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * <p>
 * 说明：
 * 给定的 n 保证是有效的。
 * <p>
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 * @author: zhongqionghua
 * @create: 2019/4/29 16:24
 */
public class NineTeen {

	public static void main(String[] args) {
		int[] linkedArray = new int[]{1, 2, 3, 4, 5};
		ListNode nodeHead = initListNode(linkedArray, 0);
		System.out.println(nodeHead.toString());
		System.out.println(removeNthFromEnd1(nodeHead, 3).toString());
	}

	/**
	 * 优化:只需要一次循环
	 * first和second指针,使得两指针之间相差位n,之后将first和second往后移,知道first==null,那么second.next就是要删除的
	 *
	 * @param head
	 * @param n
	 * @return
	 */
	public static ListNode removeNthFromEnd1(ListNode head, int n) {
		ListNode dummy = new ListNode(0);
		dummy.setNext(head);
		ListNode first = dummy;
		ListNode second = dummy;
		for (int i = 1; i <= n + 1; i++) {
			first = first.getNext();
		}
		while (first != null) {
			first = first.getNext();
			second = second.getNext();
		}
		second.setNext(second.getNext().getNext());
		return dummy.getNext();
	}

	/**
	 * 两次遍历方法
	 *
	 * @param head
	 * @param n
	 * @return
	 */
	public static ListNode removeNthFromEnd(ListNode head, int n) {
		//链表的长度
		ListNode dummy = new ListNode(0);
		dummy.setNext(head);
		int length = 0;
		ListNode first = head;
		while (first != null) {
			length++;
			first = first.getNext();
		}
		//倒数n位,正数多少位
		length -= n;
		first = dummy;
		while (length > 0) {
			length--;
			first = first.getNext();
		}
		first.setNext(first.getNext().getNext());
		return dummy.getNext();
	}

	public static ListNode initListNode(int[] intArray, int index) {
		ListNode node;
		if (index == intArray.length - 1) {
			node = new ListNode(intArray[index]);
		} else {
			node = new ListNode(intArray[index]);
			node.setNext(initListNode(intArray, index + 1));
		}
		return node;
	}


}


//class ListNode {
//	int val;
//	ListNode next;
//
//	ListNode(int x) {
//		val = x;
//	}
//}


