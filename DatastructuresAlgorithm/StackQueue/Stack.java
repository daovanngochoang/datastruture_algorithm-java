package DatastructuresAlgorithm.StackQueue;

public interface Stack<E> {
    public void push(E elem);
    public E pop();
    public boolean isEmpty();
    public E top();
    public int size();
    public void clear();
}
