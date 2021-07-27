package Datastructures.BST;

public class BNode {
    int element;
    BNode leftChild;
    BNode rightChild;
    BNode parentNode;

    BNode(int element, BNode leftChild, BNode rightChild, BNode parentNode) {
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

    public BNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BNode leftChild) {
        this.leftChild = leftChild;
    }

    public BNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(BNode rightChild) {
        this.rightChild = rightChild;
    }

    public BNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(BNode parentNode) {
        this.parentNode = parentNode;
    }

    public boolean isLeaf(){
        return this.leftChild ==null && this.rightChild ==null;
    }

}
