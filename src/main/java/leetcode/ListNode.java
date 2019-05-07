package leetcode;

/**
 * @desc: 类描述
 * @author: zhongqionghua
 * @create: 2019/4/30 15:09
 */
public class ListNode {
	private int val;
	private ListNode next;

	public ListNode() {
	}

	ListNode(int x) {
		val = x;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public ListNode getNext() {
		return next;
	}

	public void setNext(ListNode next) {
		this.next = next;
	}

	@Override
	public String toString() {
		StringBuffer stringBuffer = new StringBuffer();
		ListNode node = this;
		while(node != null){
			stringBuffer.append(node.getVal() + "->");
			node = node.getNext();
		}
		String result = stringBuffer.toString();
		return result.substring(0, result.length() - 2);
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