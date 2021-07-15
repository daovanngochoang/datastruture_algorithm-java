package Datastructures.List;



public class LinkedNode<E> {

    E element;
    LinkedNode<E> next;
    LinkedNode<E> previous;

    public LinkedNode(E e){
        this.setElement(e);
        this.setNext(null);
    }

    public E getElement() {
        return element;
    }

    public void setElement(E element) {
        this.element = element;
    }

    public LinkedNode<E> getNext() {
        return next;
    }

    public void setNext(LinkedNode<E> next) {
        this.next = next;
    }

}
