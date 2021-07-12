package Datastructures.List;



public class LinkedNode<E> {


    E element;
    LinkedNode<E> next;
    LinkedNode<E> previous;

    LinkedNode(E e){
        this.setElement(e);
        this.setNext(null);
        this.setPrevious(null);
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

    public LinkedNode<E> getPrevious() {
        return previous;
    }

    public void setPrevious(LinkedNode<E> previous) {
        this.previous = previous;
    }
}
