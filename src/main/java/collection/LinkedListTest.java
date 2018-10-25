package collection;

import com.alibaba.fastjson.JSONObject;

import java.util.LinkedList;

/**
 * @Description: LinkedListTest类
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/25 17:30
 */
public class LinkedListTest {

	public static void main(String[] args) {
		// 测试LinkedList的API
		testLinkedListAPIs();

		// 将LinkedList当作 LIFO(后进先出)的堆栈
		useLinkedListAsLIFO();

		// 将LinkedList当作 FIFO(先进先出)的队列
		useLinkedListAsFIFO();
	}

	/**
	 * 测试LinkedList中部分API
	 */
	private static void testLinkedListAPIs() {
		LinkedList strList = new LinkedList();
		strList.add("1");
		strList.add("2");
		strList.add("3");
		System.out.println("list:" + JSONObject.toJSONString(strList));

		//add
		strList.add(1, "4");
		System.out.println("list:" + JSONObject.toJSONString(strList));

		//addFirst
		strList.addFirst("10");
		System.out.println("list:" + JSONObject.toJSONString(strList));

		//removeFirst
		System.out.println("strList.removeFirst():" + strList.removeFirst());
		System.out.println("list:" + JSONObject.toJSONString(strList));

		//getFirst
		System.out.println("strList.getFirst():" + strList.getFirst());

		//offerFirst
		strList.offerFirst("10");
		System.out.println("list:" + JSONObject.toJSONString(strList));

		//pollFirst
		System.out.println("strList.pollFirst():" + strList.pollFirst());
		System.out.println("list:" + JSONObject.toJSONString(strList));

		// peekFirst
		System.out.println("strList.peekFirst():" + strList.peekFirst());
		System.out.println("list:" + JSONObject.toJSONString(strList));

		//addLast
		strList.addLast("20");
		System.out.println("list:" + JSONObject.toJSONString(strList));

		// removeLast
		System.out.println("strList.removeLast():" + strList.removeLast());
		System.out.println("list:" + JSONObject.toJSONString(strList));

		// getLast
		System.out.println("strList.getLast():" + strList.getLast());
		System.out.println("list:" + JSONObject.toJSONString(strList));


		// offerLast
		strList.offerLast("20");
		System.out.println("list:" + JSONObject.toJSONString(strList));

		// pollLast
		System.out.println("strList.pollLast():" + strList.pollLast());
		System.out.println("list:" + JSONObject.toJSONString(strList));

		// peekLast
		System.out.println("strList.peekLast():" + strList.peekLast());
		System.out.println("list:" + JSONObject.toJSONString(strList));

		// set
		strList.set(2, "300");
		System.out.println("get(3):" + strList.get(2));

		// 将LinkedList转行为数组
		String[] arr = (String[]) strList.toArray(new String[0]);
		for (String str : arr) {
			System.out.println("str:" + str);
		}
		System.out.println("size:" + strList.size());

		// 清空LinkedList
		strList.clear();
		// 判断LinkedList是否为空
		System.out.println("isEmpty():" + strList.isEmpty() + "\n");

	}

	/**
	 * 将LinkedList当作 LIFO(后进先出)的堆栈
	 */
	private static void useLinkedListAsLIFO() {
		System.out.println("useLinkedListAsLIFO");
		LinkedList stack = new LinkedList();
		stack.push("1");
		stack.push("2");
		stack.push("3");
		stack.push("4");
		System.out.println("list:" + JSONObject.toJSONString(stack));
		// 删除“栈顶元素”
		System.out.println("stack.pop():" + stack.pop());
		// 取“栈顶元素”
		System.out.println("stack.peek():" + stack.peek());
		System.out.println("list:" + JSONObject.toJSONString(stack));
	}

	/**
	 * 将LinkedList当作 FIFO(先进先出)的队列
	 */
	private static void useLinkedListAsFIFO() {
		System.out.println("\nuseLinkedListAsFIFO");
		LinkedList queue = new LinkedList();
		queue.add("10");
		queue.add("20");
		queue.add("30");
		queue.add("40");
		System.out.println("list:" + JSONObject.toJSONString(queue));
		System.out.println("queue.remove():" + queue.remove());
		System.out.println("queue.element():" + queue.element());
		System.out.println("queue:" + queue);
	}
}
