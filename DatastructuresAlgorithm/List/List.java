package DatastructuresAlgorithm.List;

public interface List<E> {

    void clear();
    boolean insert(int index, E item);
    boolean append(E item); //add to the end
    int size();
    boolean isEmpty();
    boolean remove(int index);
    E get(int index);
    boolean set(int index, E item);


}
