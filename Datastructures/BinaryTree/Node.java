package Datastructures.BinaryTree;

public class Node {
    int element;
    Node leftChild;
    Node rightChild;
    Node parentNode;
    boolean isLeaf = true;

    Node (int element, Node leftChild, Node rightChild, Node parentNode) {
        this.element = element;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
        this.parentNode = parentNode;

    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
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

    public Node getParentNode() {
        return parentNode;
    }

    public void setParentNode(Node parentNode) {
        this.parentNode = parentNode;
    }

    public boolean isLeaf(){
        return isLeaf;
    }

    public void setLeaf(boolean leaf) {
        isLeaf = leaf;
    }
}
