package Datastructures.BalancedBST;

public class NodeAVL {
    NodeAVL parent;
    NodeAVL leftChild;
    NodeAVL rightChild;
    int element;
    int height;

    public NodeAVL(int elem, NodeAVL parent) {
        this.parent = parent;
        this.leftChild = null;
        this.rightChild = null;
        this.height = 0;
        this.element = elem;
    }

    public NodeAVL getParent() {
        return parent;
    }

    public void setParent(NodeAVL parent) {
        this.parent = parent;
    }

    public NodeAVL getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(NodeAVL leftChild) {
        this.leftChild = leftChild;
    }

    public NodeAVL getRightChild() {
        return rightChild;
    }

    public void setRightChild(NodeAVL rightChild) {
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



