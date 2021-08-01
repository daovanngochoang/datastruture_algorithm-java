package DatastructuresAlgorithm.List;

import java.util.Arrays;

public class SingleLinkedListImp<E> implements List<E>{
    /*
    all the information of linked list can be found here : https://www.tutorialspoint.com/data_structures_algorithms/linked_list_algorithms.htm
     */

    LinkedNode<E> head;
    LinkedNode<E> tail;
    int size;


    SingleLinkedListImp(){
        this.size = 0;
        this.head=this.tail=null;

    }

    @Override
    public void clear() {
        this.head = this.tail = null;
        size = 0;
    }

    @Override
    public boolean insert(int index, E item) {
        LinkedNode<E> current = this.head;

        if (!(this.isEmpty()) && index >= 0 && index < size){ // if index is in range of array.

            if (index == size-1){ // if index is the last element
                append(item); // then append it
                return true;
            }

            for (int i = 0; i < index; i++){// else
                current = current.next;
            }
            LinkedNode<E> newItem = new LinkedNode<>(item); // assign new item
            newItem.setNext(current.getNext()); // set item to point at the next item of the current
            current.setNext(newItem); // set the next of current is the new item

            size++;
            return true;
        }

        System.out.printf("index %d out of range %d \n\n", index, size-1);
        return false;
    }

    public void appendMany(E[] Array){
        for (E i : Array){
            this.append(i);
        }
    }

    @Override
    public boolean append(E item) {

        if (this.size == 0){ // if there is no item ==> initial assign head = tail = new item

            this.head = new LinkedNode<>(item);
            this.tail = head;
            this.size ++;
            return true;
        }else {
            this.tail.setNext(new LinkedNode<>(item)); // else add new item after tail and set tail to be the new item
            this.tail = this.tail.getNext();
            size ++;
            return true;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(int index) {
        LinkedNode<E> current = this.head;

        if (index == 0){ // if remove the head
            LinkedNode<E> temp = this.head.getNext(); //
            this.head.setNext(null); // set head to be null
            this.head = temp;
            size--;

        }else if (index > 0 && index < size){
            for (int i = 0; i < index-1 ; i++){
                current = current.next;
            }

            System.out.println("deleted : "+ current.next.element);
            if (index == size-1){
                this.tail = current;

            }
            current.setNext(current.next.next);
            size--;
            return true;
        }


        System.out.printf("index %d out of range %d \n\n", index, size-1);
        return false;
    }

    @Override
    public E get(int index) {
        LinkedNode<E> current = head;
        if (!(this.isEmpty()) && index >= 0 && index < size) {
            if (index == 0) {
                return head.element;
            }
            else if (index == size - 1) {
                return tail.element;
            }
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            return current.getElement();
        }
        return null;
    }

    @Override
    public boolean set(int index, E item){
        LinkedNode<E> current = head;
        if (!(this.isEmpty()) && index >= 0 && index < size){
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }
            current.setElement(item);
        }
        return false;
    }

    public LinkedNode<E> getHead() {
        return head;
    }

    public void setHead(LinkedNode<E> head) {
        this.head = head;
    }

    public LinkedNode<E> getTail() {
        return tail;
    }

    public void setTail(LinkedNode<E> tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String toString(){
        LinkedNode<E> current = head;
        E[] array = (E[]) new Object[size];
        for (int i = 0; i < size; i++){
            array[i] = current.getElement();
            current = current.getNext();
        }
        return Arrays.toString(array);
    }

    public static void main(String[] args){
        SingleLinkedListImp<Integer> Ll = new SingleLinkedListImp<>();
        Integer[] a = {1,1,-4, -2, 6, 3, 2, 5, 9, 7, 25, 15, 14, 100, 80, 56, 477, 34, 563, 342,3,4,5,6,73,4,2,45,7};
        Ll.appendMany(a);
        System.out.println(Ll);
        Ll.remove(11);
        System.out.println(Ll);
        Ll.insert(10, 12);
        Ll.insert(11, 13);
        Ll.set(12, 0);

        System.out.println(Ll);
        System.out.println(Ll.tail.element);
        System.out.println(Ll.get(12));

    }
}
