package Datastructures.BalancedBST;

public class AVLNode {
    AVLNode parent;
    AVLNode leftChild;
    AVLNode rightChild;
    int element;
    int height;

    public AVLNode(int elem, AVLNode parent) {
        this.parent = parent;
        this.leftChild = null;
        this.rightChild = null;
        this.height = 0;
        this.element = elem;
    }

    public AVLNode getParent() {
        return parent;
    }

    public void setParent(AVLNode parent) {
        this.parent = parent;
    }

    public AVLNode getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(AVLNode leftChild) {
        this.leftChild = leftChild;
    }

    public AVLNode getRightChild() {
        return rightChild;
    }

    public void setRightChild(AVLNode rightChild) {
        this.rightChild = rightChild;
    }

    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isLeaf() {
        return this.leftChild == null && this.rightChild ==null;
    }
}



