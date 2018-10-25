package tree.binary.common;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 测试
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/23 16:31
 */
public class Client {

	public static void main(String[] args) {
		Tree bt = new Tree(100);
		bt.insert(580);
		bt.insert(12);
		bt.insert(50);
		bt.insert(58);
		bt.insert(9);
		bt.insert(888);
		bt.insert(248);
		bt.insert(32);
		bt.insert(666);
		bt.insert(455);
		bt.insert(777);
		bt.insert(999);
		bt.insert(900);
		List<Integer> intList = new ArrayList<>();

		//中序（递归）
		bt.inOrderTraverse(bt.getRoot(), intList);
		System.out.println("中序递归:" + JSONObject.toJSON(intList));
		//中序（非递归）
		intList.clear();
		bt.inOrderByStack(intList);
		System.out.println("中序非递归:" + JSONObject.toJSON(intList));

		//前序遍历
		intList.clear();
		bt.preOrderTraverse(bt.getRoot(), intList);
		System.out.println("前序递归:" + JSONObject.toJSON(intList));
		//前序非递归
		intList.clear();
		bt.preOrderByStack(intList);
		System.out.println("前序非递归:" + JSONObject.toJSON(intList));

		//后续遍历
		intList.clear();
		bt.postOrderTraverse(bt.getRoot(), intList);
		System.out.println("后序递归:" + JSONObject.toJSON(intList));
		//后序非递归
		intList.clear();
		bt.postOrderByStack(intList);
		System.out.println("后序非递归:" + JSONObject.toJSON(intList));

		//树中最小值
		System.out.println("树中最小值为：" +  bt.getMinValue());
		//树中最大值
		System.out.println("树中最小值为：" +  bt.getMaxValue());

		/*
		1.删除叶子节点
		2.删除有一个子结点的节点
		3.删除有两个子结点的节点
		 */
		intList.clear();
		bt.inOrderTraverse(bt.getRoot(), intList);
		System.out.println("删除之前,root:+" + bt.getRoot() + "|中序:" + JSONObject.toJSON(intList));
		intList.clear();
		boolean deleteFlag = bt.delete(100);
		System.out.println("删除操作状态：" + deleteFlag);
		bt.inOrderTraverse(bt.getRoot(), intList);
		System.out.println("删除之后, root:" + bt.getRoot() +"|中序:" + JSONObject.toJSON(intList));

	}
}
