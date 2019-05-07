package leetcode;

/**
 * @description: 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，
 * 并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * @author: zhongqionghua
 * @Date: 2019/4/22 16:49
 */
public class Second {
	public static void main(String[] args) {
		ListNode node1 = initListNode(new int[]{2, 6, 7});
		ListNode node2 = initListNode(new int[]{0, 9, 4});
		ListNode mergeNode = mergeListNode(node1, node2);
		System.out.println(mergeNode.toString());
	}


	public static ListNode mergeListNode(ListNode one, ListNode two) {
		int intoNum = 0;
		ListNode first = null;
		ListNode pre = null;
		while (one != null || two != null || intoNum > 0) {
			int addNum = addParamByListNode(one, two);
			boolean isGreatTen = addNum >10;
			ListNode targetListNode = new ListNode((isGreatTen ? addNum - 10 : addNum) + intoNum);
			one = one == null ? null : one.getNext();
			two = two == null ? null : two.getNext();
			intoNum = (isGreatTen ? 1 : 0);
			if(pre != null){
				pre.setNext(targetListNode);
			}else{
				first = targetListNode;
			}
			pre = targetListNode;
		}
		return first;
	}

	/**
	 * 生成listNode数据
	 *
	 * @param intArray
	 * @return
	 */
	public static ListNode initListNode(int[] intArray) {
		ListNode node = null;
		if (intArray == null || intArray.length == 0) {
			return node;
		}
		ListNode first = null;
		ListNode pre = null;
		for (int i = 0; i < intArray.length; i++) {
			node = new ListNode(intArray[i]);
			if (pre != null) {
				pre.setNext(node);
			}
			pre = node;
			if(i == 0){
				first = node;
			}
		}
		return first;
	}

	public static int addParamByListNode(ListNode one, ListNode two) {
		return (one == null ? 0 : one.getVal()) + (two == null ? 0 : two.getVal());
	}
}


