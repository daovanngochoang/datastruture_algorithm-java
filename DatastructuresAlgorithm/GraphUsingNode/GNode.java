package DatastructuresAlgorithm.GraphUsingNode;


import java.util.Arrays;
import java.util.LinkedList;


class GNode<E> implements Comparable<GNode<E>>{
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

    @Override
    public String toString() {
        return  item + " weight=" + weight + " Edges=" + Edges +" previous=" + previous;
    }


    @Override
    public int compareTo(GNode gNode) {
        return 0;
    }
}