package Sorts;

import java.util.ArrayList;
import java.util.Arrays;

public class InsertionSort {

    /*
    - insertion sort is likely as the selection sort.
    - there are 2 part including sorted part (in the left)  and the unsorted part (in the right).
    - the step of this algorithm:
        + assume the left sorted part = null.
        + the right unsorted part is the whole array.
        + run a loop through the right part to each item which is called the current working item.
        + in each item, we compare them with the items in the left sorted part.
        + and we change the order of the current item with the items in the left sorted part if the current item is smaller.
        and one problem is the index of items which is bigger than the current will + 1 because we insert the current item
        before it's bigger item in the sorted part. ==> to make it easy, we loop from the right to the left of the sorted part.

        => the previous item is the item in index of the current - 1.
        if that item is bigger than the current ==> the previous + 1 = current item's position.

        Below algorithms is implemented for array and array list.

        time complexity: best , average, worst = O(n), O(n^2), O(n^2) respectively.

     */

    public void insertionSortArrayFor(int[] array){
        int length = array.length;
        int item, index;

        for (int i = 0; i<length; i++){ // loop to the right part which is unsorted, i is the index of current working item.
            item = array[i]; // assign a value to the variable item, which is the current working item to sort.
            index = i;
            for (int j = i; j > 0 ; j--){ // loop in the left part (sorted part)
                if (item < array[j-1]){/* compare if the item is smaller than the previous item in the sorted part
                    change order, by checking from the right to the left of the sorted part, if the items before is bigger
                    than the current checking item ==> move up 1 index. */

                    array[j] = array[j-1];/* j is the index from 0 to i in array, since j = i, => j -- will move the index to the left.
                    we also can move to the right.*/

                    index = j -1; // record the final position.
                }
            }
            array[index] = item; // set final position.
        }
    }

    public void insertionSortArrayWhile(int[] array){ // the logic background is the same as using for.
        int len = array.length;
        int rIndex = 0, item , lIndex, index; // initial declare var of right index, item, left index, final index.

        while (rIndex < len){ // loop through the array
            lIndex = index = rIndex; // assign all index.
            item = array[rIndex]; // assign item = the current item that to insert in the sorted part.

            while (lIndex > 0){ // loop in the sorted part.

                if (item < array[lIndex -1]){
                    /* compare the current item with the items in the sorted part to find the correct position.
                    if the current item < the item of the current - 1 index position ==> move + 1 to the current position.
                    * */
                    array[lIndex] = array[lIndex-1];
                    index = lIndex - 1;
                }
                lIndex --;
            }

            array[index] = item;
            rIndex ++;
        }

    }

    public void insertionSortArrayList(ArrayList<Integer> arrayList){
        int length = arrayList.size();
        int item, index;

        for (int i = 0; i<length; i++){ // loop to the right part which is unsorted, i is the index of current working item.
            item = arrayList.get(i); // assign a value to the variable item, which is the current working item to sort.
            index = i;
            for (int j = i; j > 0 ; j--){ // loop in the left part (sorted part)
                if (item < arrayList.get(j-1)){/* compare if the item is smaller than the previous item in the sorted part
                    change order, by checking from the right to the left of the sorted part, if the items before is bigger
                    than the current checking item ==> move up 1 index. */

                    arrayList.set(j, arrayList.get(j-1));/* j is the index from 0 to i in arrayList, since j = i, => j -- will move the index to the left.
                    we also can move to the right.*/

                    index = j -1; // record the final position.
                }
            }
            arrayList.set(index, item); // set final position.
        }

    }





    public static void main(String[] args){
        int[] array = {1,1,-4, -2, 6, 3, 2, 5, 9, 7, 25, 15, 14, 100, 80, 56, 477, 34, 563, 342,3,4,5,6,73,4,2,45,7};
        int[] array1 = {1,1,-4, -2, 6, 3, 2, 5, 9, 7, 25, 15, 14, 100, 80, 56, 477, 34, 563, 342,3,4,5,6,73,4,2,45,7};

        ArrayList<Integer> a = new ArrayList<>();
        int count = 0;
        while (count < array.length){
            a.add(array[count]);
            count ++;
        }


        InsertionSort sort = new InsertionSort();

        sort.insertionSortArrayList(a);
        sort.insertionSortArrayWhile(array);
        sort.insertionSortArrayFor(array1);
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.toString(array1));
        System.out.println(a);

    }
}
