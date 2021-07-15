package Datastructures.StackQueue;

public interface Queue<E> {
    public void enqueue(E elem);
    public E dequeue();
    public boolean isEmpty();
    public E element();
    public void clear();
    public int size();
}
