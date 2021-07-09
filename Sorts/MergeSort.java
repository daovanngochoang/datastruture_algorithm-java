package Sorts;

import java.util.ArrayList;
import java.util.Arrays;

public class MergeSort {



    /*
    - original algorithm.
        - suppose that we have an array mA = { 9, 7, 25, 15, 14, 100, 80, 56 }
        - we break it into parts.
        - to demonstrate this i will rewrite the algorithm:

        - mA = { 9, 7, 25, 15, 14, 100, 80, 56 }  ==> divide the array into parts until it has 1 item.
        - { 9, 7, 25, 15 } & { 14, 100, 80, 56 }
        - { 9, 7 } & { 25, 15 }    { 14, 100 } & { 80, 56 }
        - {9} & {7}  {25} & {15}   {14} & {100} {80} & {56}
        now, we merge these in order smaller to larger number.
        by comparing items in each pair of these parts, smaller is added order to the new merged array, then we have.

        - { 7, 9 } { 15, 25 } { 14, 100 } { 56, 80 }
        - { 7, 9, 15, 25 } { 14, 56, 80, 100}
        - { 7, 9, 14, 15, 25, 56, 80, 100}


    - bottom-up method.
        - we merge the array from width = 1.
        - instead of dividing the array, we can loop to the array and handing each of these pairs in order.

        we also have it's index. leftIndex, rightIndex and the end is the index of the left part, index of the right part
        and the end of the right index, respectively .

        for example: this array { 9, 7, 25, 15, 14, 100, 80, 56 , -4, -2, 6, 3}

        - width = 1 ==> it break the array into every single peaces.

            { 9, 7, 25, 15, 14, 100, 80, 56, -4, -2, 6, 3 } => it will break array in each pair.
            => leftIndex = 0 and right = 1 , leftIndex = 2 and rightIndex = 3 ...
        - width = 2.
            => leftIndex = 0 and right = 2 , leftIndex = 4 and rightIndex = 6 ...

        ==> leftIndex = leftIndex + 2*width.
        ==> rightIndex =  leftIndex + width.
        ==> end = leftIndex + 2*width.

        - { 9 } & {7}, {25} & {15}, {14} & {100}, {80} & {56} , {-4} & {-2}, {6} & {3}
          <--------->  <--------->   <--------->   <------->     <-------->   <------->
        - { 7, 9 } & { 15, 25 },  { 14, 100 } & { 56, 80 },  {-4, -2} & {3, 6}
           <---------------->       <------------------>      <------------->
        - { 7, 9, 15, 25 } & { 14, 56, 80, 100},              {-2, -4, 3, 6}
          <--------------------------------->               // in this problem, we see that
                                                               the last array has only the left sub-array because it out of range
                                                               ==> the rightIndex will be equal to min of rightIndex and array length
                                                                   the end will also equal to min of end with the array length.
        - { 7, 9, 14, 15, 25, 56, 80, 100}  &  {-2, -4, 3, 6}
           <---------------------------------------------->

        ==> {-2, -4, 3, 6, 7, 9, 14, 15, 25, 56, 80, 100} bingo !

     * the merge algorithm.
        - after break into pair of sub-arrays, we must do the merging step.
        for example: this 2 array { 14, 100 } & { 56, 80 } will be merged in to an array with order-arrangement.
        we compare each item of the left with item in the right. and add to a merged array.

        so we have index of the left, index of the right initially assign equal to 0 or equal to the index of parts in array.
        - (IF) the left is not fully added (AND) (the condition of the right is fully added (OR) the left item is smaller the the
        right item) (THEN) the left item will be added into the merged array, (ELSE) the right item will be added the the merged array.

        the primary logic:
            - smaller first.
            - add the remaining part after one of these array is fully added.
     */


    // this is the merge sort implemented by myself using recursion.
    public int[] MergeSortArrayRecursion(int[] array){

        int len = array.length , midIndex = len / 2;
        int [] leftArray, rightArray;

        if (len <= 1){ // the stop condition of recursion invoking.
            return array;
        }else {

            leftArray = Arrays.copyOfRange(array, 0, midIndex); // left array will be from 0 to middle index.
            rightArray = Arrays.copyOfRange(array, midIndex, len); // the remaining part of array.
            leftArray = MergeSortArrayRecursion(leftArray); // call itself for manipulation the left
            rightArray = MergeSortArrayRecursion(rightArray); // for the right and both return result to the previous invoking.
        }
        return mergeSubarray(leftArray, rightArray, len);
    }


    // merge sub-array of recursion algorithm.
    public int[] mergeSubarray(int[] leftArray, int[] rightArray, int len){ // merge the sub-arrays.

        int leftQuantity= 0, rightQuantity = 0, leftLen = leftArray.length, rightLen = rightArray.length; //  we know the quantity to control the sub-arrays
        int [] finalArray = new int[len]; // final array will have the len of original array

        for (int i = 0; i < len ; i ++){
            // loop to the merged array to arrange the value to it in order.

            if (leftQuantity < leftLen && (rightQuantity == rightLen
                    || leftArray[leftQuantity] <= rightArray[rightQuantity]) ){
                /* if the left side is not fully added AND ( the right side is fully added OR the item in the left smaller than the item in the right
                 this using Short-circuit technique which divide the logic into :

                 - case 1: leftIndex <  leftArray.length which mean the left array is not fully added and rightQuantity == rightArray.length
                 mean that the array is fully added ==> add the remaining in the left part.

                 - case 2: the left array is not fully added and the item in left array smaller the item in right array
                 ==> add that number to the final-array
                 */

                finalArray[i] = leftArray[leftQuantity]; // add the
                leftQuantity++; // update the control index of sub-array

            }else {
                // case 1: if the left is fully added ==> add the remaining in the right
                // case 2: any case that right item <= the left item then add that number to merged array.
                finalArray[i] = rightArray[rightQuantity];
                rightQuantity++; // update the control index of sub-array
            }

        }
        return finalArray;
    }

    /*
    the code bellow is the full expressing steps look like, it run correctly as well.


    public int[] mergeSubarray(int[] leftArray, int[] rightArray, int len){ // merge the sub-arrays, len is the len of the parent array,
        // sum of len sub-arrays = len of parent array.

        boolean flag = true;
        int leftQuantity= 0, rightQuantity = 0; //  we know the quantity to control the sub-arrays
        int [] finalArray = new int[len]; // final array will have the len of original array

            while (flag){

                if (leftQuantity != leftArray.length && rightQuantity != rightArray.length){ // if both of them is not fully added.

                    if (leftArray[leftQuantity] <= rightArray[rightQuantity]){ // if the left item <=
                        finalArray[leftQuantity + rightQuantity] = leftArray[leftQuantity];
                        leftQuantity++;
                    }else {
                         // if the right item <

                        finalArray[rightQuantity + leftQuantity] = rightArray[rightQuantity];
                        rightQuantity++;
                    }
                }else if (leftQuantity == leftArray.length){ // left is fully added.

                    for (int i = rightQuantity; i < rightArray.length; i++){ // add the remaining part of the right
                        finalArray[leftQuantity + rightQuantity] = rightArray[i];
                        rightQuantity++;
                    }
                }else { // right is fully added.

                    for (int i = leftQuantity; i < leftArray.length; i++){ // add the remaining part of the left.
                        finalArray[leftQuantity + rightQuantity] = leftArray[i];
                        leftQuantity++;
                    }
                }
                // check if left and right is fully added or not.if the left item <
                if (leftQuantity + rightQuantity == len){
                    flag = false;
                }
            }
            return finalArray;
        }
        */


    public int[] MergeSortArrayNonRecur( int[] array){ // this is the bottom-up merge algorithm.
        int leftIndex, len = array.length;
        int[] backUpArray= new int[len];

        for (int width = 1; width<len; width*=2){

            for (leftIndex = 0; leftIndex < len; leftIndex += width*2){
                merge(array, backUpArray, leftIndex, leftIndex + width, Math.min(len, leftIndex+width*2));
            }
            array = Arrays.copyOf(backUpArray, len);
        }

        return array;
    }

    public static void merge(int[] array, int[] backUpArray ,int leftIndex, int rightIndex, int end){
        // it has the same technique as the merging technique of recursion.
        int mid = rightIndex;

        for (int i = leftIndex; i < end; i++){
            if (leftIndex < mid && (rightIndex >= end || array[leftIndex] < array[rightIndex])){
                backUpArray[i] = array[leftIndex];
                leftIndex++;
            }else {
                backUpArray[i]  = array[rightIndex];
                rightIndex++;
            }

        }
    }



    public static void main(String[] args){
        int[] a = {1,1,-4, -2, 6, 3, 2, 5, 9, 7, 25, 15, 14, 100, 80, 56, 477, 34, 563, 342,3,4,5,6,73,4,2,45,7};
        int[] b = {1,1,-4, -2, 6, 3, 2, 5, 9, 7, 25, 15, 14, 100, 80, 56, 477, 34, 563, 342,3,4,5,6,73,4,2,45,7};

        MergeSort sort = new MergeSort();
        a = sort.MergeSortArrayRecursion(a);
        System.out.println(Arrays.toString(a) + " len : " + a.length);
        b = sort.MergeSortArrayNonRecur(b);
        System.out.println(Arrays.toString(b) + " len : " + b.length);


    }
}

