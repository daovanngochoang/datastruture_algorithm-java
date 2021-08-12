package DynamicProgramming.MaximumNetworkFlow.OneWayOnlyMethod;




import java.util.LinkedList;

public class sort<E>{
    public void QuickSortTraditionalMethodNonRecursion(LinkedList<Edge<E>> array){

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

        int len = array.size(), start = 0, end = len-1, lTop = -1, hTop = -1, pivotIndex;
        if (len >= 2) {
            int[] lowStack = new int[len / 2], highStack = new int[len / 2];

            // initial put the item in stack
            lowStack[++lTop] = start; // the start index in the lower.
            highStack[++hTop] = end; // the ending index in the higher.


            while (lTop > -1 && hTop > -1) { // we will do all sub-partitions in the index until it done.

                start = lowStack[lTop--]; // pop out item
                end = highStack[hTop--];

                pivotIndex = partitionArray(array, start, end); // put into the partition function for handling partitions

                if (pivotIndex - 1 > start) { // if the item from pivot - 1 > start ==> that mean there are items in that side
                    lowStack[++lTop] = start; // add start
                    highStack[++hTop] = pivotIndex - 1; // add end
                }

                if (pivotIndex + 1 < end) {// same for the higher side
                    lowStack[++lTop] = pivotIndex + 1; // add start
                    highStack[++hTop] = end; // add end
                }
            }
        }

    }

    public int partitionArray(LinkedList<Edge<E>> array, int lIndex , int hIndex){

        Edge<E> pivot = array.get(hIndex);
        int rightCounter = hIndex;
        Edge<E> temp;
        int pivotPosition = hIndex;

        for (int leftCounter = lIndex; leftCounter < hIndex; leftCounter++){ // run leftCounter to array

            if (array.get(leftCounter).Capability < pivot.Capability) { // if leftCounter found an item that > pivot ==> rightCounter run
                while (leftCounter < rightCounter) { // rightCounter run but make sure it not pass the leftCounter
                    if (array.get(rightCounter).Capability > pivot.Capability) { // if rightCounter found an item < pivot => swap them and break.
                        temp = array.get(leftCounter);
                        array.set(leftCounter, array.get(rightCounter));
                        array.set(rightCounter, temp);
                        break;
                    }
                    rightCounter--;
                }
                if (!(leftCounter < rightCounter)) { // if leftCounter pass the rightCounter => swap the leftCounter with pivot.
                    array.set(pivotPosition, array.get(leftCounter));
                    array.set(leftCounter, pivot);
                    pivotPosition = leftCounter;
                    break;
                }
            }

        }

        return pivotPosition;// return pivot position.
    }

}
