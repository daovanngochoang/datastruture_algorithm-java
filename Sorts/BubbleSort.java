package Sorts;

import java.util.ArrayList;
import java.util.Arrays;

/*
- bubble sort algorithm is comparing and swap each pair of element in an array.
- for example:
        we have an array which is int A = {3,2,5,1,3,7}
        we have n elements of A. => this algorithm run n steps.
        the first step, it check the first element and its adjacent, in this case 3 and 2.
            then check if the first element > second element, swap order.
            3 > 2 => swap order of 3 to 2 and 2 to 3.
            and go on to all of element.
        => n elements = n step. each of n step it check and swap n elements.
        ==> O(n^2) time.

* */

public class BubbleSort {

    public void BubbleSortArray(int[] array ){
        int length = array.length;
        int temp;

        for (int i = 0; i < length; i ++){ // loop to n item in the array
            for (int j = 0; j < length - 1;j ++){ // and each time the process in the item, it start to compare each pair

                if (array[j] > array[j+1]){ // if the first > the next ==> swap order.
                        temp = array[j]; // store the first in to a temp value.
                        array[j] = array[j+1]; // set the first to be the second order
                        array[j+1] = temp; // set the second to be the first order.

                }
            }
        }
    }

    public void BubbleSortArrayList(ArrayList<Integer> array ){
        int length = array.size();
        int temp;

        for (int i =0; i<length; i++) {
            for (int j = 0; j < length - 1; j++) {
                if (array.get(j) > array.get(j + 1)) {

                    temp = array.get(j);
                    array.set(j, array.get(j + 1));
                    array.set(j + 1, temp);
                }
            }
        }
    }

    public static void main(String[] args){
        int[] array = {1,1,-4, -2, 6, 3, 2, 5, 9, 7, 25, 15, 14, 100, 80, 56, 477, 34, 563, 342,3,4,5,6,73,4,2,45,7};
        BubbleSort Sort = new BubbleSort();

        Sort.BubbleSortArray(array);
        System.out.println(Arrays.toString(array));


        ArrayList<Integer> arrayList = new ArrayList<>();
        int length = array.length;
        for (int j : array) {
            arrayList.add(j);
        }

        Sort.BubbleSortArrayList(arrayList);
        System.out.println(arrayList);
    }
}
