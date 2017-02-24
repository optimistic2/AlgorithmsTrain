package two;

public class BST<Key extends Comparable<Key>, Value> { 
	private Node root;
	
	private class Node {
		private Key key;			//键
		private Value value;		//值
		private Node left;			//指向子树的链接
		private Node right;			//指向右树的链接
		private int N;				//以该节点为根的子树中的节点总数
		public Node(Key key, Value value, int N) {
			this.key = key;
			this.value = value;
			this.N = N;
		}
	}
	public int size() {
		return size(root);
	}
	private int size(Node x) {
		if(x==null) {
			return 0;
		}else {
			return x.N;
		}
	}
	public Value get(Key key) {
		return get(root, key);
	}
	private Value get(Node x, Key key) {
		if(x==null) {
			return null;
		}
		int cmp = key.compareTo(x.key);
		if(cmp<0) {
			return get(x.left, key);
		}else if(cmp>0) {
			return get(x.right, key);
		}else {
			return x.value;
		}
	}
	public void put(Key key, Value value) {
		root = put(root, key, value);
	}
	
	private Node put(Node x, Key key, Value value) {
		if(x==null) {
			return new Node(key, value, 1);
		}
		int cmp = key.compareTo(x.key);
		if(cmp<0){
			x.left = put(x.left, key, value);
		}else if(cmp>0) {
			x.right = put(x.right, key, value);
		}else {
			x.value = value;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
}
