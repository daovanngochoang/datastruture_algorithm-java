package Datastructures.SplayTree.myTechnique;

public class SNode<E> {

    int key;
    E element;
    SNode<E> leftChild;
    SNode<E> rightChild;
    SNode<E> parent;


    public SNode (int key, E elem, SNode<E> parent){
        this.key = key;
        this.element = elem;
        this.parent = parent;
        this.leftChild = this.rightChild = null ;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public SNode<E> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(SNode<E> leftChild) {
        this.leftChild = leftChild;
    }

    public SNode<E> getRightChild() {
        return rightChild;
    }

    public void setRightChild(SNode<E> rightChild) {
        this.rightChild = rightChild;
    }

    public SNode<E> getParent() {
        return parent;
    }

    public void setParent(SNode<E> parent) {
        this.parent = parent;
    }

    public boolean isLeaf(){
        return this.leftChild == null && this.rightChild == null;
    }
}
