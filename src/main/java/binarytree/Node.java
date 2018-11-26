package binarytree;

import java.io.Serializable;

/**
 * @Description: 树节点
 * @Author: zhongqionghua
 * @CreateDate: 2018/10/23 15:33
 */
public class  Node implements Serializable {
	private static final long serialVersionUID = -169594254550616724L;
	/**
	 * 节点值
	 */
	private Integer value;
	/**
	 * 左子节点
	 */
	private Node leftChild;
	/**
	 * 右子节点
	 */
	private Node rightChild;

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public Node(int value) {
		this.value = value;
	}

	public Node(Integer value, Node leftChild, Node rightChild) {
		this.value = value;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public Node getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	public Node getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}

	@Override
	public boolean equals(Object obj) {
		Node param = (Node)obj;
		return param.getValue().equals(value);
	}
}
