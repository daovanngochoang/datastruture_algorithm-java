package DatastructuresAlgorithm.List;


import java.util.Arrays;

public class ArrayListImplement<E> implements List<E>{


    /*
    - ArrayList is implement of List using array.
    - manage size, insert is easier than traditional array.
    - insert a element to array, we move all item after that index + 1 step
    - remove ==> reduce index of all position after that index 1 step.
    - append will add item to the end of array.

     */

    private static final int defaultSize = 10;
    private int listSize;
    private int maxSize;
    private E[] listArray;


    public ArrayListImplement(){
        this(defaultSize);
    }
    public ArrayListImplement(int size){

        maxSize = size;
        listSize = 0;
        listArray= (E[]) new Object[size];
    }

    @Override
    public void clear() { // remove all element in the list
        listSize = 0;
    }

    public void appendMany ( E[] a){
        for (E it : a){
            this.append(it);
        }
    }


    @Override
    public boolean insert(int index, E item) {

        if (listSize < maxSize){ // if size smaller than maxsize ==> do as normal
            if (index >= 0 && index < listSize){ // if index is in the list ==> insert
                for (int i = listSize; i > index; i--){
                    listArray[i] = listArray[i-1]; // move all element after the index up 1 more index
                }
                listArray[index] = item; // insert item to that index
                listSize ++; // increase size
                return true;
            }

        } else { // if listSize is equal to maxsize ==> expand size.
            maxSize += defaultSize; // expand the limit of maxsize
            listArray = Arrays.copyOf(listArray, maxSize); // increase space
            insert(index, item); // recursively call itself to handle the list after increase maxsize
            return true;
        }

        return false;
    }

    @Override
    public boolean append(Object item) { // append to the end
        if (listSize < maxSize){ // if listSize is still smaller than max size
            listArray[listSize++] = (E) item; // then just put it in and increase listSize
        }else { // if listSize is exceed the maxsize ==> increase the size and call itself to handle the list after expanding
            maxSize += defaultSize;
            listArray = Arrays.copyOf(listArray, maxSize);
            append(item);
        }
        return false;
    }

    @Override
    public int size() {
        return listSize;
    }

    @Override
    public boolean isEmpty() {
        if (listSize == 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(int index) { // remove element
        for(int i = index; i < listSize-1; i++){ // move all element after that item down 1 position.
            listArray[i] = listArray[i+1];
        }
        listSize --; // reduce size
        return false;
    }

    @Override
    public E get(int index) {

        return listArray[index];
    }

    @Override
    public boolean set(int index, E item) {
        if (index >= 0 && index < listSize){
            listArray[index] = (E) item;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        E[] printArray = Arrays.copyOf(listArray, listSize);
        return  Arrays.toString(printArray) ;
    }


    public void showArray(){
        System.out.println(Arrays.toString(listArray));
    }

    public static void main (String[] args){

        ArrayListImplement<Integer> a = new ArrayListImplement<>();
        Integer[] array = {1, 2, 3, 4, 5, 11, 6, 7, 8, 9, 10, 2, 3, 4, 5, 11, 6, 7, 8, 9};
        a.appendMany(array);
        System.out.println(a.listSize);

        a.showArray();
        a.remove(5);
        System.out.println(a);




    }

}
