package Sorts;

import java.util.ArrayList;
import java.util.Arrays;

public class SelectionSort {

    /*
    - selection sort is a easy sort algorithm, which is divide the array into 2 part including sorted part and unsorted part.
    - the sorted part initially is empty, and the unsorted part is the whole array.
    for example: [5,3,4,8,1,2]
                 [|5,3,4,8,1,2] => the first part is sorted which is empty, and the remaining part is the unsorted part.
                 the checking value is the value that currently is checked.

    - firstly, the algorithm will assume the min value of the array will be the first value of unsorted part which i call "the checking value", in this case
     "min = unsorted array's first element = 5", the sorted part will be from the first position to "the checking value" position to the left and
    the unsorted part is the remaining of the array to the right.

    - then it search for the min value in the unsorted part, if there is any value that smaller than min ==> reassign min equal to that value.
            + in our example, min = unsorted array's first element =  the checking value = 5, and it loop in the unsorted array.
            3 < 5 => min = 3;
            .....
            min = 1.
    - swap order (position) of the checking value with the min value if there are any number in unsorted part smaller initial min.


    - best, average, worst case = O(n^2)
     */

    public boolean isArraySorted(int[] array){
        int len = array.length;
        for (int i = 0; i < len -1; i ++ ){
            if (array[i] > array[i+1]){
                System.out.println("unsorted");
                return false;
            }

        }
        System.out.println("sorted");

        return  true;
    }

    public boolean isArrayListSorted(ArrayList<Integer> arrayList){
        int len = arrayList.size();
        for (int i = 0; i < len -1; i ++ ){
            if (arrayList.get(i) > arrayList.get(i+1)){
                System.out.println("unsorted");
                return false;
            }

        }
        System.out.println("sorted");

        return  true;
    }

    public void SelectionSortArray(int[] array){ // sort array

        int ArrayLength = array.length;
        int temp, MinIndex = 0; // initial declare min and temporary variable
        boolean flag = false;

        if (!isArraySorted(array)) {

            for (int i = 0; i < ArrayLength - 1; i++) { // loop into the array
                MinIndex = i; // assign min = the first element of the unsorted array.

                for (int j = i + 1; j < ArrayLength; j++) { // loop throw the unsorted part which is the remain array.
                    if (array[j] < array[MinIndex]) { // check if any value smaller than initial min.
                        flag = true; // if there is an min ==> flag = true
                        MinIndex = j; // reassign min index for swapping process.
                    }
                }

                // swap order of i and the min value.
                if (flag) { // if there is a min value => swap and else do nothing.
                    temp = array[i];
                    array[i] = array[MinIndex];
                    array[MinIndex] = temp;
                    flag = false;
                }
            }
            System.out.println("sorted = " + Arrays.toString(array));
        }
    }

    public void SelectionSortArrayList(ArrayList<Integer> arrayList ) {
        int ArrayLength = arrayList.size();
        int temp, MinIndex = 0; // initial declare min and temporary variable
        boolean flag = false;

        if (!isArrayListSorted(arrayList)) {
            for (int i = 0; i < ArrayLength - 1; i++) { // loop into the array
                MinIndex = i; // assign min = the first element of the unsorted array.

                for (int j = i + 1; j < ArrayLength; j++) { // loop throw the unsorted part which is the remain array.
                    if (arrayList.get(j) < arrayList.get(MinIndex)) { // check if any value smaller than initial min.
                        flag = true; // if there is an min ==> flag = true
                        MinIndex = j; // reassign min index for swapping process.

                    }
                }

                // swap order of i and the min value.
                if (flag) { // if there is a min value => swap and else do nothing.
                    temp = arrayList.get(i);
                    arrayList.set(i, arrayList.get(MinIndex));
                    arrayList.set(MinIndex, temp);
                    flag = false;
                }
            }
            System.out.println("sorted = " + arrayList);
        }
    }


    public static void main(String[] args){
        SelectionSort sort = new SelectionSort();
        int[] array = {1,1,-4, -2, 6, 3, 2, 5, 9, 7, 25, 15, 14, 100, 80, 56, 477, 34, 563, 342,3,4,5,6,73,4,2,45,7};
        int[] carray = {-4, -2, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 6, 7, 7, 9, 14, 15, 25, 34, 45, 56, 73, 80, 100, 342, 477, 563};


//        sort.SelectionSortArray(array);
        sort.SelectionSortArray(carray);
        ArrayList<Integer> list = new ArrayList<>();

        for (int i : array){
            list.add(i);
        }

        sort.SelectionSortArrayList(list);
    }

}
