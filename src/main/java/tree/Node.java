package tree;

public class Node {
	
	private int value;
	private Node parent;
	private Node leftChild;
	private Node rightChild;
	
	public Node(int value){
		this.value=value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
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
	public boolean equals(Object o){
		Node other = (Node) o;
		if(other.getValue() != this.getValue()){
			return false;
		}
		if(other.getLeftChild() != null && this.getLeftChild() == null){
			return false;
		}
		else if(other.getRightChild() != null && this.getRightChild() == null){
			return false;
		}
		else if(other.getRightChild() == null && this.getRightChild() == null){
			return true;
		}
		else if ((other.getLeftChild() !=null && this.getLeftChild() != null && other.getLeftChild().getValue() != this.getLeftChild().getValue()) || 
				other.getRightChild() !=null && this.getRightChild() != null && other.getRightChild().getValue() != this.getRightChild().getValue()){
			return false;
		}
		return true;
	}
	
	

}
