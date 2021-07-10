package Sorts;

import java.util.Arrays;

public class QuickSort {
    /*
    this method is choosing pivot in the middle of each sub-array. it need extra array.
     */
    public int[] QuickSortArrayMidRecursion(int[] array){
        int len = array.length, middleIndex = (len -1) / 2, lowerQuantity =0, higherQuantity =0;
        int[] lower = new int[len], higher = new int[len];

        if(len <= 2){
            int temp;
            if (len == 2){
                if (array[0] > array[1]){
                    temp = array[1];
                    array[1] = array[0];
                    array[0] = temp;
                }
            }
            return array;
        }
        else{

            for (int i =0; i < len; i++){
                if (i != middleIndex){
                    if (array[i] < array[middleIndex]){
                            lower[lowerQuantity++] = array[i];
                    }else {
                        higher[higherQuantity++] = array[i];
                    }
                }
            }
            lower[lowerQuantity++] = array[middleIndex];
            lower = Arrays.copyOf(lower, lowerQuantity);
            higher = Arrays.copyOf(higher, higherQuantity);

//            System.out.println(Arrays.toString(lower) + " " + Arrays.toString(higher));
            lowerQuantity = lower.length;

            if (lower.length == 0){
                higher = QuickSortArrayMidRecursion(higher);

            }else if (higher.length == 0){
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



    public void QuickSortArrayMidNonRecursion(int[] array){
        int start, end, len = array.length, mid, pivot, right, left, leftTop = -1, rightTop = -1;
        int[] leftStack = new int[len/2], rightStack = new int[len/2], backUp, newArray = new int[len];

        start = 0;
        end = len;

        leftStack[++leftTop] = start;
        rightStack[++rightTop] = end;


        while (leftTop > -1 && rightTop > -1 ){

            start = leftStack[leftTop--] ;
            end = rightStack[rightTop--];
            mid = (end+start)/2;
            pivot = start;
            right = 0;
            backUp = new int[end - start];


            for (int i = start; i < end; i++){
                if (i!= mid){
                    if(array[i] < array[mid]){
                        newArray[pivot++] = array[i];
                    }else{
                        backUp[right++] = array[i];
                    }
                }
            }

            newArray[pivot++] = array[mid];
            left = pivot;

            if (pivot - 2 > start){
                leftStack[++leftTop] = start;
                rightStack[++rightTop] = pivot;
            }
            if (pivot + 1 < end){
                leftStack[++leftTop] = pivot;
                rightStack[++rightTop] = end;
            }

            for (int i = 0; i<right; i++){
                newArray[left++] = backUp[i];
            }

            array = Arrays.copyOf(newArray, newArray.length);
        }
        System.out.println(Arrays.toString(array));

    }



    public static void main(String[] args){
        int[] array = {1,1,-4, -2, 6, 3, 2, 5, 9, 7, 25, 4, 3, 5, 2, 1, 3, 2, 3, 15, 14, 100, 80, 56, 477, 34, 563, 342,3,4,5,6,73,4,2,45,7,1,1,-4, -2, 6, 3, 2, 5, 9, 7, 25, 15, 14, 100, 80, 56, 477, 34, 563, 342,3,4,5,6,73,4,2,45,7};


        QuickSort sort = new QuickSort();
        sort.QuickSortArrayMidNonRecursion(array);
        array = sort.QuickSortArrayMidRecursion(array);

        System.out.println(Arrays.toString(array));
    }
}
