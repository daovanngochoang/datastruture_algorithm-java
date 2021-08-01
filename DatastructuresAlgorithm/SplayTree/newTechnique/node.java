package DatastructuresAlgorithm.SplayTree.newTechnique;

public class node<E> {

    int key;
    E element;
    node<E> leftChild;
    node<E> rightChild;


    public node(int key, E elem) {
        this.key = key;
        this.element = elem;
        this.leftChild = this.rightChild = null;
    }

}
