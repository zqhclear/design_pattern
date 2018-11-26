package binarytree.sort;

import binarytree.Node;

import java.io.Serializable;
import java.util.List;
import java.util.Stack;

/**
 * @Description: 树：包含查找，插入，删除，遍历等基本操作
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/23 15:40
 */
public class SortedTree implements Serializable {
	private static final long serialVersionUID = 7340884555170949444L;

	/**
	 * 根节点
	 */
	private Node root;

	public SortedTree(int nodeValue) {
		root = new Node(nodeValue);
	}

	/**
	 * 根据传入的值查找结点(二叉排序树?)
	 *
	 * @param nodeValue
	 * @return Node 当返回null时，表示未查找到
	 */
	public Node findNode(int nodeValue) {
		Node currentNode = root;
		while (true) {
			if (nodeValue == root.getValue()) {
				return root;
			} else if (nodeValue < root.getValue()) {
				currentNode = root.getLeftChild();
			} else if (nodeValue > root.getValue()) {
				currentNode = root.getRightChild();
			}
			if (currentNode == null) {
				return null;
			}
		}
	}

	/**
	 * 将nodeValue插入树中(二叉排序树)
	 *
	 * @param nodeValue
	 * @return String 插入成功返回null,不成功返回错误信息
	 */
	public String insert(int nodeValue) {
		String error = null;
		Node node = new Node(nodeValue);
		//当前树为空？
		if (root == null) {
			root = node;
		} else {
			Node current = root;
			Node parent = null;
			while (true) {
				if (current.getValue() == nodeValue) {
					return "sorry,insert failed! having same value in tree.";
				}
				//插入节点小于父节点值，插入左侧
				if (nodeValue < current.getValue()) {
					parent = current;
					current = current.getLeftChild();
					//当左侧节点为空时，表示node就是该位置
					if (current == null) {
						parent.setLeftChild(node);
						break;
					}
				}
				//插入节点于父节点值，插入右侧
				if (nodeValue > current.getValue()) {
					parent = current;
					current = current.getRightChild();
					//当右侧节点为空时，则表示node就是该位置
					if (current == null) {
						parent.setRightChild(node);
						break;
					}
				}
			}
		}
		return error;
	}

	/**
	 * 中序遍历（递归方式）
	 * 1.调用自身来遍历节点的左子树
	 * 2.访问这个节点
	 * 3.调用自身来遍历节点的右子树
	 */
	public void inOrderTraverse(Node node, List<Integer> treeValueList) {
		if (node == null) {
			return;
		}
		inOrderTraverse(node.getLeftChild(), treeValueList);
		treeValueList.add(node.getValue());
		inOrderTraverse(node.getRightChild(), treeValueList);
	}

	/**
	 * 中序遍历（非递归方式）
	 * 1.对于任意节点current，若该节点不为空则将该节点压栈，并将左子树节点置为current，重复此操作，直到current为空。
	 * 2.若左子树为空，栈顶节点出栈，访问节点后将该节点的右子树置为current
	 * 3.重复1、2步操作，直到current为空且栈内节点为空。
	 *
	 * @param treeValueList
	 */
	public void inOrderByStack(List<Integer> treeValueList) {
		Stack<Node> nodeStack = new Stack<>();
		Node current = root;
		while (current != null || !nodeStack.isEmpty()) {
			while (current != null) {
				nodeStack.push(current);
				current = current.getLeftChild();
			}
			if (!nodeStack.isEmpty()) {
				current = nodeStack.pop();
				treeValueList.add(current.getValue());
				current = current.getRightChild();
			}
		}
	}

	/**
	 * 前序遍历（递归方式）
	 */
	public void preOrderTraverse(Node node, List<Integer> treeValueList) {
		if (node == null) {
			return;
		}
		treeValueList.add(node.getValue());
		preOrderTraverse(node.getLeftChild(), treeValueList);
		preOrderTraverse(node.getRightChild(), treeValueList);
	}

	/**
	 * 前序遍历（非递归方式）
	 *
	 * @param treeValueList
	 */
	public void preOrderByStack(List<Integer> treeValueList) {
		Node current = root;
		Stack<Node> nodeStack = new Stack<>();
		while (current != null || !nodeStack.isEmpty()) {
			while (current != null) {
				nodeStack.push(current);
				treeValueList.add(current.getValue());
				current = current.getLeftChild();
			}
			if (!nodeStack.isEmpty()) {
				current = nodeStack.pop();
				current = current.getRightChild();
			}
		}
	}

	/**
	 * 后序遍历（递归方式）
	 */
	public void postOrderTraverse(Node node, List<Integer> treeValueList) {
		if (node == null) {
			return;
		}
		postOrderTraverse(node.getLeftChild(), treeValueList);
		postOrderTraverse(node.getRightChild(), treeValueList);
		treeValueList.add(node.getValue());
	}

	/**
	 * 后序遍历（非递归方式）
	 *
	 * @param treeValueList
	 */
	public void postOrderByStack(List<Integer> treeValueList) {
		Node current = root;
		Stack<Node> nodeStack = new Stack<>();
		Node preNode = null;
		while (current != null || !nodeStack.isEmpty()) {
			while (current != null) {
				nodeStack.push(current);
				current = current.getLeftChild();
			}
			if (nodeStack.isEmpty()) {
				continue;
			}
			/*
			这一步精髓，需要一个preNode来记录上一次存的node节点值，来防止多次存储
			current == preNode表示上一布已经将该元素存储了，避免重复记录
			 */
			current = nodeStack.peek().getRightChild();
			if (current == null || current == preNode) {
				current = nodeStack.pop();
				treeValueList.add(current.getValue());
				preNode = current;
				current = null;
			}
		}
	}

	/**
	 * 查找树中最小值
	 *
	 * @return Integer 当返回null是表示树为空
	 */
	public Integer getMinValue() {
		Node current = root;
		while (true) {
			if (current == null) {
				return null;
			}
			if (current.getLeftChild() == null) {
				return current.getValue();
			}
			current = current.getLeftChild();
		}
	}

	/**
	 * 查找树中最大值
	 *
	 * @return Integer 当返回null时表示树为空
	 */
	public Integer getMaxValue() {
		Node current = root;
		while (true) {
			if (current == null) {
				return null;
			}
			if (current.getRightChild() == null) {
				return current.getValue();
			}
			current = current.getRightChild();
		}
	}

	/**
	 * 删除节点
	 * 分三种情况：1删除的节点没有子节点；2.删除的节点有一个左子节点或者一个右子节点；3.删除的节点有两个子节点
	 *
	 * @param value
	 * @return boolean true:删除成功/false:删除失败
	 */
	public boolean delete(int value) {
		if (root == null) {
			return false;
		}
		//1.删除的节点没有子节点
		Node current = root;
		//记录value对应节点的父节点
		Node parentNode = null;
		//记录value对应的节点是左子节点还是右子节点
		boolean isLeftChild = false;
		//查找value对应的节点位置,并且要记录找到的节点的父节点和该节点为左还是右
		while (true) {
			if (value == current.getValue()) {
				break;
			} else if (value < current.getValue()) {
				parentNode = current;
				current = current.getLeftChild();
				isLeftChild = true;
			} else if (value > current.getValue()) {
				parentNode = current;
				current = current.getRightChild();
				isLeftChild = false;
			}
			//未找到value对应的Node
			if (current == null) {
				return false;
			}
		}
//		//条件1
//		deleteWithoutChild(current, parentNode, isLeftChild);
//		//条件2
//		deleteOneChild(current, parentNode, isLeftChild);
		//条件3：已经扩展，可以满足上方两个条件
		deleteTwoChild(current, parentNode, isLeftChild);
		return false;
	}

	/**
	 * 条件2：删除节点有两个子节点
	 *
	 * @param current
	 * @param parentNode
	 * @param isLeftChild
	 */
	private void deleteTwoChild(Node current, Node parentNode, boolean isLeftChild) {
		Node target = selectSuccessorNodeForDelete(current);
		//删除的节点为根节点
		if (root.equals(current)) {
			root = target;
		} else {
			if (isLeftChild) {
				parentNode.setLeftChild(target);
			} else {
				parentNode.setRightChild(target);
			}
		}
	}

	/**
	 * 删除current节点，查找对应的替换节点
	 *
	 * @param current 需要删除的节点
	 * @return Node 替换节点
	 */
	private Node selectSuccessorNodeForDelete(Node current) {
		Node target = selectMaxLeftChild(current);
		if (target == null) {
			target = selectMinRightChild(current);
		}
		//当target==null,则表示该删除的节点没有左右子节点
		return target;
	}

	/**
	 * 找到节点current左子节点中最大值,并删除（准备迁移）
	 *
	 * @param current
	 * @return Node 当返回null，表示查找错误
	 */
	private Node selectMaxLeftChild(Node current) {
		if (current == null || current.getLeftChild() == null) {
			return null;
		}
		Node leftChild = current.getLeftChild();
		Node parent = current;
		//一直查找右节点
		while (leftChild.getRightChild() != null) {
			parent = leftChild;
			leftChild = leftChild.getRightChild();
		}
		/*
		将删除节点的左右子节点赋值给目标节点
		<此处要注意，很特殊的情况，删除节点的左子节点只有一个值，那么，不能直接将左子节点的位置赋值，
		而要赋值他左子节点的左子节点，这种情况赋值为null>
		 */
		if (!current.getLeftChild().equals(leftChild)) {
			//将该节点从其父节点中删除
			parent.setRightChild(null);
			leftChild.setLeftChild(current.getLeftChild());
		}
		leftChild.setRightChild(current.getRightChild());
		//返回目标节点
		return leftChild;
	}

	/**
	 * 找到节点current右子节点中最小值，并删除（准备迁移）
	 *
	 * @param current
	 * @return Node 当返回null，表示查找错误
	 */
	private Node selectMinRightChild(Node current) {
		if (current == null || current.getRightChild() == null) {
			return null;
		}
		Node rightChild = current.getRightChild();
		Node parent = current;
		//一直查找右节点
		while (rightChild.getLeftChild() != null) {
			parent = rightChild;
			rightChild = rightChild.getLeftChild();
		}
		/*
		将删除节点的左右子节点赋值给目标节点
		<此处要注意，很特殊的情况，删除节点的右子节点只有一个值，那么，不能直接右子节点的位置赋值，
		而要赋值他右子节点的右子节点，这种情况赋值为null>
		 */
		if (!current.getRightChild().equals(rightChild)) {
			//将该节点从父节点中移除
			parent.setLeftChild(null);
			rightChild.setRightChild(current.getRightChild());
		}
		rightChild.setLeftChild(current.getLeftChild());
		//返回目标节点
		return rightChild;
	}

	/**
	 * 条件2：删除节点有一个子节点（左子节点/右子节点）
	 *
	 * @param current
	 * @param parent
	 * @param isLeftChild
	 */
	private void deleteOneChild(Node current, Node parent, boolean isLeftChild) {
		//被删除的节点有右子节点
		if (current.getRightChild() != null && current.getLeftChild() == null) {
			if (root.equals(current)) {
				root = current.getRightChild();
			} else {
				if (isLeftChild) {
					parent.setLeftChild(current.getRightChild());
				} else {
					parent.setRightChild(current.getRightChild());
				}
			}
		}
		//被删除的节点有左子节点
		if (current.getRightChild() == null && current.getLeftChild() != null) {
			if (root.equals(current)) {
				root = current.getLeftChild();
			} else {
				if (isLeftChild) {
					parent.setLeftChild(current.getLeftChild());
				} else {
					parent.setRightChild(current.getLeftChild());
				}
			}
		}
	}

	/**
	 * 条件1删除节点：该节点没有子节点
	 *
	 * @param current
	 * @param parent
	 * @param isLeftChild
	 */
	private void deleteWithoutChild(Node current, Node parent, boolean isLeftChild) {
		//删除的节点-没有子节点
		if (current.getLeftChild() == null && current.getRightChild() == null) {
			//如果是根节点，则将根节点置为null
			if (current.equals(root)) {
				root = null;
			} else {
				if (isLeftChild) {
					parent.setLeftChild(null);
				} else {
					parent.setRightChild(null);
				}
			}
		}
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

}
