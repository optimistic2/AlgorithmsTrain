package two;

import java.io.Serializable;

public class BinaryTreeNode implements Serializable {
	private static final long serialVersionUID = -2879767221438363678L;

	private BinaryTreeNode root;
	
	private int key;
	
	private Object value;
	
	private BinaryTreeNode left;
	
	private BinaryTreeNode right;
	
	private int num;

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public BinaryTreeNode getLeft() {
		return left;
	}

	public void setLeft(BinaryTreeNode left) {
		this.left = left;
	}

	public BinaryTreeNode getRight() {
		return right;
	}

	public void setRight(BinaryTreeNode right) {
		this.right = right;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
}
