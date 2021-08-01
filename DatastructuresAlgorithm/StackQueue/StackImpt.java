package DatastructuresAlgorithm.StackQueue;

import DatastructuresAlgorithm.List.LinkedNode;

import java.util.Arrays;

public class StackImpt<E> implements Stack<E> {

    LinkedNode<E> top;
    int size;

    public StackImpt() {
        this.top = null;
        this.size = 0;
    }

    @Override
    public void push(E elem) {
        if (this.size == 0){
            this.top = new LinkedNode<>(elem);
            this.size++;
        }else{
            LinkedNode<E> newNode = new LinkedNode<>(elem);
            newNode.setNext(top);
            this.top = newNode;
            this.size ++;
        }
    }

    @Override
    public E pop() {

        if(this.top != null){
            LinkedNode<E> node = this.top;
            this.top = this.top.getNext();
            node.setNext(null);
            this.size --;
            System.out.println(node.getElement());
            return node.getElement();
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public E top() {
        return this.top.getElement();
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void clear() {
        for (int i = size; i > 0; i--){
            this.pop();
        }
    }
    public String toString(){
        E[] a = (E[]) new Object[size];
        LinkedNode<E> curr = this.top;
        for (int i =1 ; i<=size; i++){
            a[size - i] = curr.getElement();
            curr = curr.getNext();
        }
        return Arrays.toString(a);
    }

    public static void main (String[] args){
        StackImpt<Integer> stAck = new StackImpt<>();
        stAck.push(1);
        stAck.push(2);
        stAck.push(3);
        stAck.push(4);
        stAck.pop();
        stAck.push(7);
        stAck.push(8);
        stAck.push(8);
        System.out.println(stAck);
        stAck.pop();


        System.out.println("size : " + stAck.size);
//        stAck.pop();
//        stAck.pop();
//        stAck.pop();
//        stAck.pop();
//        stAck.clear();
        System.out.println(stAck);

    }
}
