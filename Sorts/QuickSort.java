package Sorts;

import java.util.Arrays;

public class QuickSort {

    /*
    WHAT IS QUICKSORT ALGORITHM ?
    - choose a pivot
    - divide the pivot into 2 part the lower part and higher part.
    - value smaller than pivot will be on the lower part, and value bigger than pivot will be store in the higher part.
    - then we do repeat the work on each part.
     */






    /*
    - this method i choose the item in the middle as pivot.
    - and run a loop into that array, and add the item that smaller than pivot to the newArray, and add the higher items
    to the backUp array.
    - we have an variable to control where the pivot is.
    - the pivot position is located after the lower items. ==> after loop, we add the pivot and the item from backUp to the lower by adding item
    from pivot to the end.
    - copy array to be as newArray.

    Example : |3|-2|11|7|-4|10|5|
    - choose 7 = pivot.
    - loop in the array and add items < pivot first, items >  pivot to backUp, pass if item is pivot .

    ==> newArray = |3|-2|-4|0|0|0|0| add pivot = 7 ==> |3|-2|-4|7|0|0|0|
    ==> backUp =   |0|0|0|0|11|18|10|

    - add backUp to newArray => newArray = |3|-2|-4|7|11|18|10|

    record index of sub-partitions using stack.
    - lower part:
        start = start;
        end = pivot;

        we get pivot as the end because our loop run from start to the end, but end = pivot - 1 ==>
        for loop will loop in item that < end ==>  item < pivot is the last item.
        example:
                |3|-2|-4|7|11|18|10|
                if we run from start = 0 (has value = 3) to pivot - 1 ==> the loop will take the pivot as the stop
                condition ==> it will not run in -4 ==> we miss an item.
                if the end is 7 ==> it will take 7 as stop condition ==> it run to all item smaller than 7 and stop at 7
                ==> all items are checked.

    - higher part:
        start = pivot +1 // because we do not need to check the pivot again.
        end = end.


     */

    public void QuickSortArrayMidNonRecursion(int[] array){

        /*
        - we have a bunch of variables here but actually it's not complicated.
        - we have start, end, mid point.
        - pivot is the position of pivot in array.
        - left is the position of items < pivot, the right item is the len of item > pivot in the backUp array.
        - leftStack to store starting indexes of sub-partition
        - rightStart to store the ending indexes.
        - newArray to store item which is arranged.

         */

        int start, end, len = array.length, mid, positionAfterPivot, highQuantity, leftTop = -1, rightTop = -1;
        int[] leftStack = new int[len/2], rightStack = new int[len/2], backUp, newArray = new int[len];

        start = 0; // initial assign start is the first item of array.
        end = len; // initial assign end is the last item + 1 which is the len of array.

        // add item to stack
        leftStack[++leftTop] = start;
        rightStack[++rightTop] = end;


        while (leftTop > -1 && rightTop > -1 ){

            // pop item out of the stack
            start = leftStack[leftTop--];
            end = rightStack[rightTop--];
            // set mid item = mid item = (end + start) / 2
            mid = (end+start)/2;
            // starting pivot is set to the first item
            positionAfterPivot = start;
            highQuantity = 0; // right = 0
            backUp = new int[end - start];


            for (int i = start; i < end; i++){ // loop to all item in array
                if (i!= mid){
                    if( array[i] < array[mid]){ // if it not pivot and smaller pivot ==> add to newArray first
                        newArray[positionAfterPivot++] = array[i];
                    }else{ // else add to backup array
                        backUp[highQuantity++] = array[i];
                    }
                }
            }

            newArray[positionAfterPivot++] = array[mid]; // aad pivot

            if (positionAfterPivot - 2 > start){ // if before pivot position has more than 1 items ==> add that address to stack
                leftStack[++leftTop] = start; // start = start item
                rightStack[++rightTop] = positionAfterPivot-1; //end = pivot position
            }
            if (positionAfterPivot < end){ // if there are items after pivot ==> add to stack
                leftStack[++leftTop] = positionAfterPivot; // start item = item after pivot
                rightStack[++rightTop] = end; // end item = the origin end.
            }

            for (int i = 0; i<highQuantity; i++){ // add the higher items that we temporary store in backUp array to newArray
                newArray[positionAfterPivot++] = backUp[i];
            }

            array = Arrays.copyOf(newArray, newArray.length); // copy all these change to the original array.
        }
        System.out.println(Arrays.toString(array));

    }


    /*
    this method is choosing pivot in the middle of each sub-array. it need extra array.
    - this method will divide item in to array instead of index address of sub-partition
    - as the above, we arrange them and copy to the lower and higher array and all it self to handle these.
     */
    public int[] QuickSortArrayMidRecursion(int[] array){
        int len = array.length, middleIndex = (len -1) / 2, lowerQuantity =0, higherQuantity =0;
        int[] lower = new int[len], higher = new int[len];

        // stop condition of recursion
        if(len <= 2){ // if the array <=2 => rearrange and return
            int temp;
            if (len == 2){
                if (array[0] > array[1]){ // swap order
                    temp = array[1];
                    array[1] = array[0];
                    array[0] = temp;
                }
            }
            return array;
        }
        else{ // if array is more than 2 items

            // choose middleIndex as pivot and  copy lower item to the lower array and higher item to higher array
            for (int i =0; i < len; i++){
                if (i != middleIndex){
                    if (array[i] < array[middleIndex]){
                        lower[lowerQuantity++] = array[i];
                    }else {
                        higher[higherQuantity++] = array[i];
                    }
                }
            }
            // add pivot to lower array
            lower[lowerQuantity++] = array[middleIndex];
            // copy and resize array
            lower = Arrays.copyOf(lower, lowerQuantity);
            higher = Arrays.copyOf(higher, higherQuantity);

//            lowerQuantity = lower.length;

            if (lowerQuantity == 0){
                higher = QuickSortArrayMidRecursion(higher);

            }else if (higherQuantity == 0){
                lower = QuickSortArrayMidRecursion(lower);

            }else {

                lower = QuickSortArrayMidRecursion(lower);
                higher = QuickSortArrayMidRecursion(higher);
            }

            array = Arrays.copyOf(lower, len);

            for (int i = 0; i < higherQuantity ; i++){
                array[lowerQuantity++] = higher[i];

            }
        }
        return array;
    }







    /*
     - the code below is implemented for the traditional method " iterative QuickSort "
     - it's not required to have extra array.
     - more optimize than the previous implementation, because of the elimination of the extra array, so we do not
     need to merge them.

     WHAT IS QUICKSORT ALGORITHM ?
     - to be clear, quicksort basically is an algorithm which choose an item in array as pivot and move the larger to
     the right, move the smaller to the left of the pivot.

     for example:
     this array : |3|-2|11|18|-4|10|p=7|

     (1.) we choose the last item is the pivot, and put all value that bigger than 7 to the right (higher), and smaller
      to the left (lower).
     then we have:
                  |3|-2|-4|p=7|11|18|10|
     now, we continue doing that for the right side and the left side.
            the right= from start to the position of pivot-1.
            the left = from pivot + 1 to the end.

            in this case: |3|-2|-4|p=7|11|18|10|
                         <-lower-->    <--higher->
            and repeat the first step (1.) to each partition, and continue doing that repeatably.

     HOW  DO WE DO THAT ?
     - Easy, we must have something called pivot, which indicate the item as a standard item, and compare every item in
     the array with the pivot.
     - we have some more things those are leftCounter to run from the left to the right and find the item that is bigger
     than the pivot, rightCounter run from the right to the left to find the smaller item compared to the pivot.

     - first, we run leftCounter, from the start index of the partition to the end, if there is an item that bigger than
     the pivot ==> the rightCounter will run until the rightCounter found an item that smaller than pivot, then swap
     these 2 items, then continue leftCounter add repeat.
     - when the leftCounter found the item that bigger than pivot but the leftCounter is passed the rightCounter, then
     swap the leftCounter position and the pivotPosition.


     One problem here, we must know the sub-partitions in each step.
     - we need to record the index of each sub-partition after swapping array's items and return the pivot.
     ==> we use stack.
     - the leftStack record the start of sub-partitions, the rightStack record the end of each sub-partition.

     the comment of the code below will explained in detail what do they do.

    */

    public void QuickSortTraditionalMethodNonRecursion(int[] array){

        /*
        -len is the index of the ending item, start is the index of the start item
        - lTop and hTop to control the item in stack or we can use stack data structure, lTop and hTop
        will initially be assigned value to -1, then if any index is put in stack, it will increase first
        for example:
            lowStack[++lTop] = item <==> lowStack[-1+1=0] = item;
            and when we pop.
            start = lowStack[lTop--]; <==> lTop = lTop - 1
            that mean the stack is pop out the value in that lTop index and it decrease, the item is no longer be useful.
        */

        int len = array.length, start = 0, end = len-1, lTop = -1, hTop = -1, pivotIndex;
        int[] lowStack = new int[len/2], highStack = new int[len/2];

        // initial put the item in stack
        lowStack[++lTop] = start; // the start index in the lower.
        highStack[++hTop] = end; // the ending index in the higher.


        while (lTop > -1 && hTop > -1){ // we will do all sub-partitions in the index until it done.

            start = lowStack[lTop--]; // pop out item
            end = highStack[hTop--];
//            lowStack[lTop+1] = 0;
//            highStack[hTop+1] = 0;

            pivotIndex = partitionArray(array, start, end); // put into the partition function for handling partitions

            if (pivotIndex - 1 > start){ // if the item from pivot - 1 > start ==> that mean there are items in that side
                lowStack[++lTop] = start; // add start
                highStack[++hTop] = pivotIndex -1; // add end
            }

            if (pivotIndex + 1 < end){// same for the higher side
                lowStack[++lTop] = pivotIndex + 1; // add start
                highStack[++hTop] = end; // add end
            }
        }
        System.out.println(Arrays.toString(array));
    }

    public static int partitionArray(int[] array, int lIndex , int hIndex){

        int pivot = array[hIndex], rightCounter = hIndex, temp, pivotPosition = hIndex;

        for (int leftCounter = lIndex; leftCounter < hIndex; leftCounter++){ // run leftCounter to array

            if (array[leftCounter] > pivot ) { // if leftCounter found an item that > pivot ==> rightCounter run
                while (leftCounter<rightCounter) { // rightCounter run but make sure it not pass the leftCounter
                    if (array[rightCounter] < pivot) { // if rightCounter found an item < pivot => swap them and break.
                        temp = array[leftCounter];
                        array[leftCounter] = array[rightCounter];
                        array[rightCounter] = temp;
                        break;
                    }
                    rightCounter--;
                }
                if (!(leftCounter < rightCounter)){ // if leftCounter pass the rightCounter => swap the leftCounter with pivot.
                    array[pivotPosition] = array[leftCounter];
                    array[leftCounter] = pivot;
                    pivotPosition = leftCounter;
                    break;
                }
            }

        }

        return pivotPosition;// return pivot position.
    }



    public void QuickSortTraditionalMethodRecursion(int[] array, int start, int end){

        if (start < end ){
            int pivotIndex = partitionArray(array, start, end);

            QuickSortTraditionalMethodRecursion(array, start, pivotIndex-1 );
            QuickSortTraditionalMethodRecursion(array, pivotIndex+1, end);
        }


    }




    public static void main(String[] args){
        int[] array = {1,1,-4, -2, 6, 3, 2, 5, 9, 7, 25, 4, 3, 5, 2, 1, 3, 2, 3, 15, 14, 100, 80, 56, 477, 34, 563, 342,3,4,5,6,73,4,2,45,7,1,1,-4, -2, 6, 3, 2, 5, 9, 7, 25, 15, 14, 100, 80, 56, 477, 34, 563, 342,3,4,5,6,73,4,2,45,7};
        int[] array1 = {1,1,-4, -2, 6, 3, 2, 5, 9, 7, 25, 4, 3, 5, 2, 1, 3, 2, 3, 15, 14, 100, 80, 56, 477, 34, 563, 342,3,4,5,6,73,4,2,45,7,1,1,-4, -2, 6, 3, 2, 5, 9, 7, 25, 15, 14, 100, 80, 56, 477, 34, 563, 342,3,4,5,6,73,4,2,45,7};
        int[] array2 = {1,1,-4, -2, 6, 3, 2, 5, 9, 7, 25, 4, 3, 5, 2, 1, 3, 2, 3, 15, 14, 100, 80, 56, 477, 34, 563, 342,3,4,5,6,73,4,2,45,7,1,1,-4, -2, 6, 3, 2, 5, 9, 7, 25, 15, 14, 100, 80, 56, 477, 34, 563, 342,3,4,5,6,73,4,2,45,7};
        int[] array3 = {1,1,-4, -2, 6, 3, 2, 5, 9, 7, 25, 4, 3, 5, 2, 1, 3, 2, 3, 15, 14, 100, 80, 56, 477, 34, 563, 342,3,4,5,6,73,4,2,45,7,1,1,-4, -2, 6, 3, 2, 5, 9, 7, 25, 15, 14, 100, 80, 56, 477, 34, 563, 342,3,4,5,6,73,4,2,45,7};

        QuickSort sort = new QuickSort();
        sort.QuickSortArrayMidNonRecursion(array);

        array = sort.QuickSortArrayMidRecursion(array1);
        System.out.println(Arrays.toString(array));

        sort.QuickSortTraditionalMethodNonRecursion(array2);

        sort.QuickSortTraditionalMethodRecursion(array3, 0, array3.length-1);
        System.out.println(Arrays.toString(array3));

    }
}
