package Datastructures.StackQueue;

import Datastructures.List.LinkedNode;

import java.util.Arrays;

public class QueueImpt<E> implements Queue<E>{

    LinkedNode<E> front;
    LinkedNode<E> back ;
    int size ;

    public QueueImpt() {
        this.front = this.back = null;
        this.size = 0;
    }

    @Override
    public void enqueue(E elem) {
        if ( this.front == null || this.back == null){
            this.front = this.back = new LinkedNode<>(elem);
        }else {
            this.front.setNext(new LinkedNode<>(elem));
            this.front = this.front.getNext();
        }
        this.size ++;
    }

    @Override
    public E dequeue() {

        if (!(this.front == null || this.back == null)){
            LinkedNode<E> cur = this.back;
            this.back = this.back.getNext();
            cur.setNext(null);
            this.size --;
            return cur.getElement();
        }

        return null;
    }

    @Override
    public boolean isEmpty() {
        return this.size==0;
    }

    @Override
    public E element() {
        if (this.back != null){
            return this.back.getElement();
        }
        return null;
    }

    @Override
    public void clear() {
        for (int i = this.size; i > 0; i--){
            this.dequeue();
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    public String toString(){
        LinkedNode<E> cur = this.back;
        E[] a = (E[]) new Object[size];
        for (int i = 0; i<size;i++){
            a[i] = cur.getElement();
            cur=cur.getNext();
        }
        return Arrays.toString(a);
    }

    public static void main (String[] args){
        QueueImpt<Integer> Queue = new QueueImpt<>();
        Queue.enqueue(1);
        Queue.enqueue(2);
        Queue.enqueue(3);
        Queue.enqueue(4);
        Queue.enqueue(5);

        System.out.println(Queue);
        Queue.clear();
        System.out.println(Queue);
        Queue.dequeue();
        Queue.dequeue();
        Queue.dequeue();
        Queue.dequeue();
        Queue.dequeue();
        Queue.dequeue();
        Queue.enqueue(1);
        Queue.enqueue(2);
        Queue.enqueue(3);
        System.out.println(Queue);




    }
}
