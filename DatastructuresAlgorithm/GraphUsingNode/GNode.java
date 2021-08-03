package DatastructuresAlgorithm.GraphUsingNode;


import java.util.Arrays;
import java.util.LinkedList;


class GNode<E> {
    E item;
    int weight;
    LinkedList<Edge<E>> Edges ;
    GNode<E> previous;

    GNode (E item){
        this.item = item;
        this.weight = Integer.MAX_VALUE;
        this.Edges = new LinkedList<>();
    }

    public void SortList (){
        sort<E> eSort = new sort<>();
        eSort.QuickSortTraditionalMethodNonRecursion(this.Edges);
    }

    public void resetWeight(){
        this.weight = Integer.MAX_VALUE;
        this.previous = null;
    }
}